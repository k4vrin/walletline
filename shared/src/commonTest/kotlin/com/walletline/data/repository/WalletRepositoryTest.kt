package com.walletline.data.repository

import app.cash.turbine.test
import com.walletline.data.local.db.WalletDataSource
import com.walletline.data.local.db.WalletLineDataSource
import com.walletline.data.mapper.toWalletEntity
import com.walletline.data.mapper.toWalletLineEntity
import com.walletline.data.util.randomUUID
import com.walletline.domain.model.wallet.Wallet
import com.walletline.domain.model.wallet.WalletLine
import com.walletline.domain.repository.WalletRepository
import io.kotest.matchers.shouldBe
import io.mockative.Mock
import io.mockative.classOf
import io.mockative.given
import io.mockative.mock
import io.mockative.once
import io.mockative.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class WalletRepositoryTest {

    @Mock
    private val walletDataSource = mock(classOf<WalletDataSource>())

    @Mock
    private val lineDataSource = mock(classOf<WalletLineDataSource>())
    private lateinit var walletRepository: WalletRepository

    @BeforeTest
    fun setup() {
        walletRepository = WalletRepositoryImpl(walletDataSource, lineDataSource)
    }


    @Test
    fun `insert wallet entity and walletLine entity successful`() = runTest {
        val wallet = wallets[0]

        given(walletDataSource)
            .coroutine { insertWallet(wallet = wallet.toWalletEntity()) }
            .then { }

        wallet.lines.map { it.toWalletLineEntity() }.forEach { walletEnt ->
            given(lineDataSource)
                .coroutine {
                    insertLine(walletEnt)
                }
                .then { }
        }

        walletRepository.insertWallet(wallet)

        verify(walletDataSource)
            .coroutine { insertWallet(wallet = wallet.toWalletEntity()) }
            .wasInvoked(once)

        wallet.lines.map { it.toWalletLineEntity() }.forEach { walletEnt ->
            verify(lineDataSource)
                .coroutine {
                    insertLine(walletEnt)
                }
                .wasInvoked(once)
        }

    }


    @Test
    fun `insert line entity successful`() = runTest {
        val wallet = wallets[0]

        wallet.lines.map { it.toWalletLineEntity() }.forEach { walletEnt ->
            given(lineDataSource)
                .coroutine {
                    insertLine(walletEnt)
                }
                .then { }
        }

        wallet.lines.forEach { walletLine ->
            walletRepository.insertLine(walletLine)
        }

        wallet.lines.map { it.toWalletLineEntity() }.forEach { walletEnt ->
            verify(lineDataSource)
                .coroutine {
                    insertLine(walletEnt)
                }
                .wasInvoked(once)
        }

    }


    @Test
    fun `getAllWallets successfully`() = runTest {

        given(walletDataSource)
            .invocation { getAllWallets() }
            .then {
                flow {
                    emit(wallets.map { it.toWalletEntity() })
                }
            }

        wallets.forEach { wallet ->
            given(lineDataSource)
                .invocation { getAllLines(wallet.id) }
                .then {
                    flow {
                        emit(
                            wallet.lines.map { it.toWalletLineEntity() }
                        )
                    }
                }
        }


        walletRepository.getAllWallets().test {
            awaitItem() shouldBe wallets
            awaitComplete()
        }

        verify(walletDataSource)
            .invocation { getAllWallets() }
            .wasInvoked(once)

        wallets.forEach { wallet ->
            verify(lineDataSource)
                .invocation { getAllLines(wallet.id) }
                .wasInvoked(once)
        }
    }

    @Test
    fun `get wallet by id successfully`() = runTest {
        val wallet = wallets[0]

        given(walletDataSource)
            .coroutine { getWalletById(wallet.id) }
            .then {
                wallet.toWalletEntity()
            }

        given(lineDataSource)
            .invocation { getAllLines(wallet.id) }
            .then {
                flow {
                    emit(wallet.lines.map { it.toWalletLineEntity() })
                }
            }


        val res = walletRepository.getWalletById(wallet.id)

        verify(walletDataSource)
            .coroutine { getWalletById(wallet.id) }
            .wasInvoked(once)

        verify(lineDataSource)
            .invocation { getAllLines(wallet.id) }
            .wasInvoked(once)

        res shouldBe wallet

    }

    @Test
    fun `get all lines by id successfully`() = runTest {
        val wallet = wallets[0]

        given(lineDataSource)
            .invocation { getAllLines(wallet.id) }
            .then {
                flow {
                    emit(wallet.lines.map { it.toWalletLineEntity() })
                }
            }


        walletRepository.getAllLines(wallet.id).test {
            awaitItem() shouldBe wallet.lines.also { println("lines: $it") }
            awaitComplete()
        }

    }


    @Test
    fun `get line by id successfully`() = runTest {
        val line = WalletLine(
            id = randomUUID(),
            walletId = "porro",
            name = "Denise Henson",
            percentage = 4541,
            balance = "1111.11",
            description = null,
            categories = listOf()
        )


        given(lineDataSource)
            .coroutine { getLineById(line.id) }
            .then {
                line.toWalletLineEntity()
            }


        val res = walletRepository.getLineById(line.id)


        verify(lineDataSource)
            .coroutine { getLineById(line.id) }
            .wasInvoked(once)

        res shouldBe line

    }

    @Test
    fun `delete wallet by id successfully`() = runTest {
        val wallet = wallets[0]


        given(walletDataSource)
            .coroutine { deleteWalletById(wallet.id) }
            .then{}

        walletRepository.deleteWalletById(wallet.id)

        verify(walletDataSource)
            .coroutine { deleteWalletById(wallet.id) }
            .wasInvoked(once)

    }

    @Test
    fun `delete line by id successfully`() = runTest {
        val line = WalletLine(
            id = randomUUID(),
            walletId = "porro",
            name = "Denise Henson",
            percentage = 4541,
            balance = "1111.11",
            description = null,
            categories = listOf()
        )


        given(lineDataSource)
            .coroutine { deleteLineById(line.id) }
            .then{}

        walletRepository.deleteLineById(line.id)

        verify(lineDataSource)
            .coroutine { deleteLineById(line.id) }
            .wasInvoked(once)

    }


    @Test
    fun `delete all wallet successfully`() = runTest {

        given(walletDataSource)
            .coroutine { deleteAllWallets() }
            .then{}

        walletRepository.deleteAllWallets()

        verify(walletDataSource)
            .coroutine { deleteAllWallets() }
            .wasInvoked(once)

    }

    @Test
    fun `delete all lines by id successfully`() = runTest {

        val id = randomUUID()
        given(lineDataSource)
            .coroutine { deleteAllLines(id) }
            .then{}

        walletRepository.deleteAllLines(id)

        verify(lineDataSource)
            .coroutine { deleteAllLines(id) }
            .wasInvoked(once)

    }

    companion object {
        val wallets = buildList {
            repeat(5) { index ->
                add(
                    Wallet(
                        id = index.toString(),
                        name = "Winnie Gilliam $index",
                        balance = "4444.44",
                        description = null,
                        lines = buildList {
                            repeat(4) { index ->
                                add(
                                    WalletLine(
                                        id = index.toString(),
                                        walletId = "porro",
                                        name = "Denise Henson",
                                        percentage = 4541,
                                        balance = "1111.11",
                                        description = null,
                                        categories = listOf()
                                    )
                                )
                            }
                        }
                    )
                )
            }
        }
    }

}