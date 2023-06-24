package com.walletline.domain.use_case.wallet.create_line

import com.walletline.data.util.randomUUID
import com.walletline.domain.model.wallet.CreateLineError
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.use_case.wallet.CreateLine
import com.walletline.domain.util.Resource
import com.walletline.util.Helpers
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class CreateLineTest {

    private lateinit var createLine: CreateLine

    @Mock
    private val walletRepository = mock(classOf<WalletRepository>())

    @BeforeTest
    fun setup() {
        createLine = CreateLine(
            walletRepository = walletRepository,
            dispatchers = Helpers.testDispatchers
        )
    }


    @Test
    fun `create line when the associated wallet does not exist return error NoWallet`() = runTest(Helpers.testDispatchers.database) {
        val wallets = buildList {
            repeat(10) { index ->
                add(
                    Wallet(
                        name = "wallet $index",
                        balance = "12345.55",
                        description = null,
                        lines = emptyList()
                    )
                )
            }
        }
        val targetWallet = wallets[0]
        val line = WalletLine(
            walletId = targetWallet.id,
            name = "needs",
            percentage = 10,
            description = null,
            categories = emptyList()
        )

        given(walletRepository)
            .coroutine { getWalletById(targetWallet.id) }
            .then { null }

        val res = createLine.execute(line)

        (res is Resource.Error) shouldBe true
        (res as Resource.Error).data shouldBe CreateLineError.NoWallet
    }

    @Test
    fun `create line when sum of percentage exceed 100 return error NotEnoughBalance`() = runTest(Helpers.testDispatchers.database) {

        val targetWallet = Wallet(
            name = "wallet",
            balance = "12345.55",
            description = null,
            lines = buildList {
                repeat(4) { ind ->
                    add(
                        WalletLine(
                            walletId = randomUUID(),
                            name = "$ind",
                            balance = "4444",
                            percentage = 25,
                            description = null,
                            categories = emptyList()
                        )
                    )
                }
            }
        )
        val line = WalletLine(
            walletId = targetWallet.id,
            name = "needs",
            percentage = 25,
            description = null,
            categories = emptyList()
        )

        given(walletRepository)
            .coroutine { getWalletById(targetWallet.id) }
            .then { targetWallet }

        val res = createLine.execute(line)

        (res is Resource.Error) shouldBe true
        (res as Resource.Error).data shouldBe CreateLineError.NotEnoughBalance
    }

    @Test
    fun `create line successfully return success true`() = runTest(Helpers.testDispatchers.database) {
        createLine = CreateLine(
            walletRepository = CreateLineTestRepo1(),
            dispatchers = Helpers.testDispatchers
        )

        val line = WalletLine(
            name = "needs",
            percentage = 25,
            description = null,
            categories = emptyList()
        )

        val res = createLine.execute(line)

        (res is Resource.Success) shouldBe true
        (res as Resource.Success).data shouldBe true
    }


    @Test
    fun `create line unsuccessfully return error ErrorHappened`() = runTest(Helpers.testDispatchers.database) {
        createLine = CreateLine(
            walletRepository = CreateLineTestRepo2(),
            dispatchers = Helpers.testDispatchers
        )

        val line = WalletLine(
            name = "needs",
            percentage = 25,
            description = null,
            categories = emptyList()
        )

        val res = createLine.execute(line)

        (res is Resource.Error) shouldBe true
        (res as Resource.Error).data shouldBe CreateLineError.ErrorHappened
    }
}