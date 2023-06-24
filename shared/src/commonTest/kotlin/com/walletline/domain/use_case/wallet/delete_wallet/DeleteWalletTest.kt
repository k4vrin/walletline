package com.walletline.domain.use_case.wallet.delete_wallet

import com.walletline.data.util.randomUUID
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.use_case.wallet.DeleteWallet
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

class DeleteWalletTest {

    private lateinit var deleteWallet: DeleteWallet
    @Mock
    private val walletRepository = mock(classOf<WalletRepository>())

    @BeforeTest
    fun setup() {
        deleteWallet = DeleteWallet(
            walletRepository = walletRepository,
            dispatchers = Helpers.testDispatchers
        )
    }

    @Test
    fun `delete wallet successfully return Resource success true`() = runTest(Helpers.testDispatchers.database) {
        val id = randomUUID()
        given(walletRepository)
            .coroutine { deleteWalletById(id = id) }
            .then {  }
        val res = deleteWallet.execute(id)

        verify(walletRepository)
            .coroutine { deleteWalletById(id) }
            .wasInvoked(once)

        (res is Resource.Success) shouldBe true
        (res as Resource.Success).data shouldBe true
    }

    @Test
    fun `delete wallet unsuccessfully return Resource error false`() = runTest(Helpers.testDispatchers.database) {
        val id = randomUUID()
        given(walletRepository)
            .coroutine { deleteWalletById(id = id) }
            .then { throw IllegalArgumentException("") }
        val res = deleteWallet.execute(id)

        verify(walletRepository)
            .coroutine { deleteWalletById(id) }
            .wasInvoked(once)

        (res is Resource.Error) shouldBe true
        (res as Resource.Error).data shouldBe false
    }
}