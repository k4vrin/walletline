package com.walletline.data.local.db

import app.cash.turbine.test
import com.walletline.data.util.randomUUID
import com.walletline.database.WalletlineDB
import com.walletline.di.util.listOfStringsAdapter
import com.walletline.util.testDBConnection
import database.WalletEntity
import database.WalletLineEntity
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class WalletLineDataSourceTest {

    private lateinit var walletlineDb: WalletlineDB
    private lateinit var walletLineDataSource: WalletLineDataSource
    private lateinit var walletDataSource: WalletDataSource
    private val wallet by lazy {
        WalletEntity(
            id = randomUUID(),
            name = "test Wallet",
            balance = "424242.42",
            description = null
        )
    }
    private val connection = testDBConnection()

    @BeforeTest
    fun setup() {
        walletlineDb = WalletlineDB(
            driver = connection,
            walletLineEntityAdapter = WalletLineEntity.Adapter(categoriesAdapter = listOfStringsAdapter)
        )

        walletLineDataSource = SqlDelightWalletLineDataSource(walletlineDb)
        walletDataSource = SqlDelightWalletDataSource(walletlineDb)
        runBlocking {
            walletDataSource
                .insertWallet(wallet)
        }
    }

    @AfterTest
    fun tearDown() {
        runBlocking {
            walletDataSource.deleteAllWallets()
        }
        connection.close()
    }

    @Test
    fun `insert line and get line by id successful`() = runTest {
        val lineId = randomUUID()
        val line = WalletLineEntity(
            id = lineId,
            wallet_id = wallet.id,
            name = "Needs",
            percentage = 20,
            balance = "101010",
            description = "",
            categories = listOf("Gym", "Coffee", "Groceries")
        )

        walletLineDataSource.insertLine(line)

        val res = walletLineDataSource.getLineById(lineId)

        res shouldBe line
    }

    @Test
    fun `insert line and get line by id null`() = runTest {
        val lineId = randomUUID()
        val line = WalletLineEntity(
            id = lineId,
            wallet_id = wallet.id,
            name = "Needs",
            percentage = 20,
            balance = "101010",
            description = "",
            categories = listOf("Gym", "Coffee", "Groceries")
        )

        walletLineDataSource.insertLine(line)

        val res = walletLineDataSource.getLineById(randomUUID())

        res shouldBe null
    }

    @Test
    fun `insert multiple line and get lines by wallet_id successful`() = runTest {

        val lines = buildList {
            repeat(4) { index ->
                add(
                    WalletLineEntity(
                        id = randomUUID(),
                        wallet_id = wallet.id,
                        name = "Needs",
                        percentage = 25,
                        balance = "101010",
                        description = "",
                        categories = listOf("Gym", "Coffee", "Groceries")
                    )
                )
            }
        }
        lines.forEach { line ->
            walletLineDataSource.insertLine(line)
        }

        walletLineDataSource.getAllLines(wallet.id).test {
            awaitItem() shouldBe lines
        }

    }


    @Test
    fun `insert line and get line and delete line by id successful`() = runTest {
        val lineId = randomUUID()
        val line = WalletLineEntity(
            id = lineId,
            wallet_id = wallet.id,
            name = "Needs",
            percentage = 20,
            balance = "101010",
            description = "",
            categories = listOf("Gym", "Coffee", "Groceries")
        )

        walletLineDataSource.insertLine(line)

        val res = walletLineDataSource.getLineById(lineId)

        res shouldBe line

        walletLineDataSource.deleteLineById(res?.id!!)

        val res2 = walletLineDataSource.getLineById(lineId)

        res2 shouldBe null

    }

    @Test
    fun `insert multiple line and get lines and delete lines by wallet_id successful`() = runTest {

        val lines = buildList {
            repeat(4) { index ->
                add(
                    WalletLineEntity(
                        id = randomUUID(),
                        wallet_id = wallet.id,
                        name = "Needs",
                        percentage = 25,
                        balance = "101010",
                        description = "",
                        categories = listOf("Gym", "Coffee", "Groceries")
                    )
                )
            }
        }
        lines.forEach { line ->
            walletLineDataSource.insertLine(line)
        }

        walletLineDataSource.getAllLines(wallet.id).test {
            awaitItem() shouldBe lines
        }

        walletLineDataSource.deleteAllLines(wallet.id)

        walletLineDataSource.getAllLines(wallet.id).test {
            awaitItem() shouldBe emptyList()
        }

    }

    @Test
    fun `category adapter encode and decode successful`() = runTest {
        val lineId = randomUUID()
        val categories = listOf("Gym", "Coffee", "Groceries")
        val line = WalletLineEntity(
            id = lineId,
            wallet_id = wallet.id,
            name = "Needs",
            percentage = 20,
            balance = "101010",
            description = "",
            categories = categories
        )

        walletLineDataSource.insertLine(line)

        walletLineDataSource.getLineById(lineId)?.categories shouldBe categories

    }
}