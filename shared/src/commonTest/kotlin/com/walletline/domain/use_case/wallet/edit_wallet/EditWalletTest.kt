package com.walletline.domain.use_case.wallet.edit_wallet

import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.use_case.wallet.EditWallet
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

class EditWalletTest {

    private lateinit var editWallet: EditWallet

    @Mock
    private val walletRepository = mock(classOf<WalletRepository>())

    @BeforeTest
    fun setup() {
        editWallet = EditWallet(
            walletRepository = walletRepository,
            dispatchers = Helpers.testDispatchers
        )
    }

    @Test
    fun `edit wallet successfully return Resource success true`() =
        runTest(Helpers.testDispatchers.database) {
            val wallet = Wallet(
                id = "sadipscing",
                name = "Dave Levy",
                balance = "laoreet",
                description = null,
                lines = listOf()
            )
            given(walletRepository)
                .coroutine { editWallet(wallet) }
                .then { }
            val res = editWallet.execute(wallet)

            verify(walletRepository)
                .coroutine { editWallet(wallet) }
                .wasInvoked(once)

            (res is Resource.Success) shouldBe true
            (res as Resource.Success).data shouldBe true
        }

    @Test
    fun `edit wallet unsuccessfully return Resource error false`() =
        runTest(Helpers.testDispatchers.database) {
            val wallet = Wallet(
                id = "sadipscing",
                name = "Dave Levy",
                balance = "laoreet",
                description = null,
                lines = listOf()
            )
            given(walletRepository)
                .coroutine { editWallet(wallet) }
                .then { throw IllegalArgumentException("")}
            val res = editWallet.execute(wallet)

            verify(walletRepository)
                .coroutine { editWallet(wallet) }
                .wasInvoked(once)

            (res is Resource.Error) shouldBe true
            (res as Resource.Error).data shouldBe false
        }
}