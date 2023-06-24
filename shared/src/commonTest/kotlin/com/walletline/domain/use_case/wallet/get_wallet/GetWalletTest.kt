package com.walletline.domain.use_case.wallet.get_wallet

import com.walletline.data.util.randomUUID
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.use_case.wallet.GetWallet
import com.walletline.domain.util.Resource
import com.walletline.util.Helpers
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.once
import io.mockative.verify
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetWalletTest {

    private lateinit var getWallet: GetWallet

    @Mock
    private val walletRepository = mock(classOf<WalletRepository>())

    @BeforeTest
    fun setup() {
        getWallet = GetWallet(
            walletRepository = walletRepository,
            dispatchers = Helpers.testDispatchers
        )
    }

    @Test
    fun `get wallet successfully return Resource success wallet`() =
        runTest(Helpers.testDispatchers.database) {
            val wallet = Wallet(
                id = "sadipscing",
                name = "Dave Levy",
                balance = "laoreet",
                description = null,
                lines = listOf()
            )
            given(walletRepository)
                .coroutine { getWalletById(wallet.id) }
                .then { wallet }
            val res = getWallet.execute(wallet.id)

            verify(walletRepository)
                .coroutine { getWalletById(wallet.id) }
                .wasInvoked(once)

            (res is Resource.Success) shouldBe true
            (res as Resource.Success).data shouldBe wallet
        }

    @Test
    fun `get not existing return Resource success null`() =
        runTest(Helpers.testDispatchers.database) {
            val id = randomUUID()
            given(walletRepository)
                .coroutine { getWalletById(id) }
                .then { null }
            val res = getWallet.execute(id)

            verify(walletRepository)
                .coroutine { getWalletById(id) }
                .wasInvoked(once)

            (res is Resource.Success) shouldBe true
            (res as Resource.Success).data shouldBe null
        }

    @Test
    fun `get wallet unsuccessfully return Resource error false`() =
        runTest(Helpers.testDispatchers.database) {
            val wallet = Wallet(
                id = "sadipscing",
                name = "Dave Levy",
                balance = "laoreet",
                description = null,
                lines = listOf()
            )
            given(walletRepository)
                .coroutine { getWalletById(wallet.id) }
                .then { throw IllegalArgumentException("") }
            val res = getWallet.execute(wallet.id)

            verify(walletRepository)
                .coroutine { getWalletById(wallet.id) }
                .wasInvoked(once)

            (res is Resource.Error) shouldBe true
            (res as Resource.Error).data shouldBe false
        }
}