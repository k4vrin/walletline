package com.walletline.data.local.db

import app.cash.turbine.test
import com.walletline.data.util.randomUUID
import com.walletline.database.WalletlineDB
import com.walletline.di.util.listOfStringsAdapter
import com.walletline.util.testDBConnection
import database.WalletEntity
import database.WalletLineEntity
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class WalletDataSourceTest {

    private lateinit var walletlineDB: WalletlineDB
    private lateinit var walletDataSource: WalletDataSource
    private val connection = testDBConnection()

    @BeforeTest
    fun setup() {
        walletlineDB = WalletlineDB(
            driver = connection,
            walletLineEntityAdapter = WalletLineEntity.Adapter(categoriesAdapter = listOfStringsAdapter)
        )

        walletDataSource = SqlDelightWalletDataSource(db = walletlineDB)
    }

    @AfterTest
    fun tearDown() {
        connection.close()
    }

    @Test
    fun `insert wallet and get wallet by id successful`() = runTest {
        val id = randomUUID()
        val wallet = WalletEntity(
            id = id,
            name = "Wallet 1",
            balance = "12345.55",
            description = null
        )

        walletDataSource.insertWallet(wallet)

        val resWallet = walletDataSource.getWalletById(id)

        resWallet shouldBe wallet
    }

    @Test
    fun `insert multiple wallet and get all wallets successful`() = runTest {
        val wallets = buildList {
            repeat(10) { index ->
                add(
                    WalletEntity(
                        id = randomUUID(),
                        name = "Wallet $index",
                        balance = "12345.55",
                        description = null
                    )
                )
            }
        }

        wallets.forEach {
            walletDataSource.insertWallet(it)
        }

        walletDataSource.getAllWallets().test {
            awaitItem() shouldBe wallets
        }

    }

    @Test
    fun `insert multiple wallet and get one wallet by id successful`() = runTest {
        val wallets = buildList {
            repeat(10) { index ->
                add(
                    WalletEntity(
                        id = randomUUID(),
                        name = "Wallet $index",
                        balance = "12345.55",
                        description = null
                    )
                )
            }
        }

        val targetWallet = wallets[2]

        wallets.forEach {
            walletDataSource.insertWallet(it)
        }

        val resWallet = walletDataSource.getWalletById(targetWallet.id)

        resWallet shouldBe targetWallet

    }

    @Test
    fun `insert multiple wallet and get one wallet by id null`() = runTest {
        val wallets = buildList {
            repeat(10) { index ->
                add(
                    WalletEntity(
                        id = randomUUID(),
                        name = "Wallet $index",
                        balance = "12345.55",
                        description = null
                    )
                )
            }
        }

        wallets.forEach {
            walletDataSource.insertWallet(it)
        }

        val resWallet = walletDataSource.getWalletById(randomUUID())

        resWallet shouldBe null

    }

    @Test
    fun `insert multiple wallet and delete one by id successful`() = runTest {
        val wallets = buildList {
            repeat(10) { index ->
                add(
                    WalletEntity(
                        id = randomUUID(),
                        name = "Wallet $index",
                        balance = "12345.55",
                        description = null
                    )
                )
            }
        }

        val targetWallet = wallets[1]

        wallets.forEach {
            walletDataSource.insertWallet(it)
        }

        walletDataSource.deleteWalletById(id = targetWallet.id)


        walletDataSource.getAllWallets().test {
            awaitItem() shouldNotContain targetWallet
        }

    }

    @Test
    fun `insert multiple wallet and delete all successful`() = runTest {
        val wallets = buildList {
            repeat(10) { index ->
                add(
                    WalletEntity(
                        id = randomUUID(),
                        name = "Wallet $index",
                        balance = "12345.55",
                        description = null
                    )
                )
            }
        }

        wallets.forEach {
            walletDataSource.insertWallet(it)
        }

        walletDataSource.deleteAllWallets()


        walletDataSource.getAllWallets().test {
            awaitItem() shouldBe emptyList()
        }

    }
}