package com.walletline.domain.use_case.wallet.get_wallets

import app.cash.turbine.test
import com.walletline.data.util.randomUUID
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.repository.WalletRepository
import com.walletline.domain.use_case.wallet.GetWallets
import com.walletline.util.Helpers
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.once
import io.mockative.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetWalletsTest {

    private lateinit var getWallets: GetWallets

    @Mock
    private val walletRepository = mock(classOf<WalletRepository>())

    @BeforeTest
    fun setup() {
        getWallets = GetWallets(
            walletRepository = walletRepository,
            dispatchers = Helpers.testDispatchers
        )
    }

    @Test
    fun `get wallets return flow list wallets`() =
        runTest(Helpers.testDispatchers.database) {
            val wallets = buildList {
                repeat(10) { index ->
                    add(
                        Wallet(
                            id = randomUUID(),
                            name = "Dave Levy $index",
                            balance = "laoreet",
                            description = null,
                            lines = listOf()
                        )
                    )
                }
            }
            given(walletRepository)
                .invocation { getAllWallets() }
                .then { flowOf(wallets) }

            val res = getWallets.execute()

            verify(walletRepository)
                .invocation { getAllWallets() }
                .wasInvoked(once)

            res.test {
                awaitItem() shouldBe wallets
                awaitComplete()
            }
        }

}