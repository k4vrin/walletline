package com.walletline.domain.use_case.wallet.create_wallet

import com.walletline.data.util.randomUUID
import com.walletline.domain.model.wallet.CreateWalletError
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.use_case.wallet.CreateWallet
import com.walletline.domain.util.Resource
import com.walletline.util.Helpers
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class CreateWalletTest {

    private lateinit var createWallet: CreateWallet

    @Mock
    private val walletRepository = mock(classOf<WalletRepository>())

    @BeforeTest
    fun setup() {
        createWallet = CreateWallet(
            walletRepository = walletRepository,
            dispatchers = Helpers.testDispatchers
        )
    }

    @Test
    fun `already existed wallet with this name return error`() =
        runTest(Helpers.testDispatchers.database) {

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
            given(walletRepository)
                .invocation { getAllWallets() }
                .then { flowOf(wallets) }

            val res = createWallet.execute(
                name = targetWallet.name,
                balance = targetWallet.balance,
                description = null,
                lines = targetWallet.lines
            )

            (res is Resource.Error) shouldBe true
            ((res as Resource.Error).data is CreateWalletError) shouldBe true
        }


    @Test
    fun `create wallet successful return success true`() = runTest(Helpers.testDispatchers.database) {

        createWallet = CreateWallet(
            walletRepository = CreateWalletTestRepo1(),
            dispatchers = Helpers.testDispatchers
        )

        val targetWallet = Wallet(
            name = "wallet ${randomUUID()}",
            balance = "12345.55",
            description = null,
            lines = emptyList()
        )

        val res = createWallet.execute(
            name = targetWallet.name,
            balance = targetWallet.balance,
            description = null,
            lines = targetWallet.lines
        )

        (res is Resource.Success) shouldBe true
        (res as Resource.Success).data shouldBe true
    }

    @Test
    fun `create wallet unsuccessful return error`() = runTest(Helpers.testDispatchers.database) {

        createWallet = CreateWallet(
            walletRepository = CreateWalletTestRepo2(),
            dispatchers = Helpers.testDispatchers
        )

        val targetWallet = Wallet(
            name = "wallet ${randomUUID()}",
            balance = "12345.55",
            description = null,
            lines = emptyList()
        )

        val res = createWallet.execute(
            name = targetWallet.name,
            balance = targetWallet.balance,
            description = null,
            lines = targetWallet.lines
        )

        (res is Resource.Error) shouldBe true
        (res as Resource.Error).data shouldBe CreateWalletError.ErrorHappened
    }
}