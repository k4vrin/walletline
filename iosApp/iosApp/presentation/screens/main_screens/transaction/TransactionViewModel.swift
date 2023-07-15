//
//  TransactionViewModel.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 7/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Foundation
import KMPNativeCoroutinesCombine
import shared

@MainActor
class TransactionViewModel: ObservableObject {
    let transactionId: String?

    @Published private(set) var state = TransactionState()
    private let effectSubject = PassthroughSubject<TransactionEffect, Never>()
    private var cancellables = Set<AnyCancellable>()

    init(transactionId: String? = nil) {
        self.transactionId = transactionId
    }

    func effect() -> AnyPublisher<TransactionEffect, Never> {
        return effectSubject.eraseToAnyPublisher()
    }

    func onEvent(_ event: TransactionEvent) {
        switch event {
        case .ChangeTitle(title: let title):
            updateState(title: title)
        case .ChangeAmount(amount: let amount):
            updateState(amount: amount)
        case .ChangeDescription(desc: let desc):
            updateState(desc: desc)
        case .ChangeLine(line: let line):
            updateSelectedLine(selectedLine: line)
        case .ChangeInitDate(date: let date):
            updateState(selectedInitialDate: date)
        case .ChangeIsTax(isTax: let isTax):
            updateState(isTaxIncluded: isTax)
        case .ChangeIsPeriodical(isPeriodical: let isPeriodical):
            updateState(isPeriodical: isPeriodical)
        case .ChangeFrequency(frequency: let frequency):
            updateState(selectedFrequency: frequency)
        case .ChangeDayFreq(frequencies: let frequencies):
            updateState(selectedCustomFrequency: frequencies)
        case .ChangePeriodicalStartDate(date: let date):
            updateState(selectedStartDate: date)
        case .ChangePeriodicalEndDate(date: let date):
            updateState(selectedEndDate: date)
        case .ChangeDuration(duration: let duration):
            updateState(selectedDuration: duration)
        case .ChangeDurationTimes(times: let times):
            updateState(selectedDurationTimes: times)
        case .ShowSomeDetial(show: let show):
            updateState(showSomeDetail: show)
        case .ShowMoreDetial(show: let show):
            updateState(showMoreDetail: show)
        case .ShowLineSheet(show: let show):
            updateState(showLineSheet: show)
        case .ShowPeriodicalSheet(show: let show):
            updateState(showPeriodicalSheet: show)
        case .ShowCategorySheet(show: let show):
            updateState(showCategorySheet: show)
        case .ShowDateSheet(show: let show):
            updateState(showDateSheet: show)
        case .CancelLineSelection:
            updateSelectedLine(selectedLine: nil)
            updateState(showLineSheet: false)
        case .SaveClicked:
            // TODO:
            doNothing()
        case .ChangeIsDeposit(isDeposit: let isDeposit):
            updateState(isDepositSelected: isDeposit)
        case .ChangeCategories(categories: let categories):
            updateState(selectedCategories: categories)
        case .CancelCategories:
            updateState(selectedCategories: [])
            updateState(showCategorySheet: false)
        case .CancelPeriodical:
            cancelPeriodical()
        case .CancelDate:
            updateState(showDateSheet: false)
            updateInitDate(selectedInitialDate: nil)
        }
    }

    func cancelPeriodical() {
        updateState(isPeriodical: false, showPeriodicalSheet: false, selectedCustomFrequency: [], selectedDurationTimes: 1)
        updateSelectedFrequency(selectedFrequency: nil)
        updateSelectedStartDate(selectedStartDate: nil)
        updateSelectedEndDate(selectedEndDate: nil)
        updateSelectedDuration(selectedDuration: nil)
    }

    private func cancelAsyncTasks() {
        cancellables.forEach { cancellable in
            cancellable.cancel()
        }
    }

    private func doNothing() {}

    private func updateSelectedDuration(selectedDuration: Duration?) {
        DispatchQueue.main.async { [self] in
            state = TransactionState(
                isDepositSelected: state.isDepositSelected,
                amount: state.amount,
                title: state.title,
                desc: state.desc,
                currencyCode: state.currencyCode,
                showSomeDetail: state.showSomeDetail,
                showMoreDetail: state.showMoreDetail,
                selectedLine: state.selectedLine,
                walletLines: state.walletLines,
                isTaxIncluded: state.isTaxIncluded,
                isPeriodical: state.isPeriodical,
                showLineSheet: state.showLineSheet,
                showCategorySheet: state.showCategorySheet,
                showPeriodicalSheet: state.showPeriodicalSheet,
                showDateSheet: state.showDateSheet,
                selectedCategories: state.selectedCategories,
                categories: state.categories,
                selectedInitialDate: state.selectedInitialDate,
                selectedFrequency: state.selectedFrequency,
                selectedCustomFrequency: state.selectedCustomFrequency,
                selectedStartDate: state.selectedStartDate,
                selectedEndDate: state.selectedEndDate,
                selectedDuration: selectedDuration,
                selectedDurationTimes: state.selectedDurationTimes
            )
        }
    }

    private func updateSelectedEndDate(selectedEndDate: Date?) {
        DispatchQueue.main.async { [self] in
            state = TransactionState(
                isDepositSelected: state.isDepositSelected,
                amount: state.amount,
                title: state.title,
                desc: state.desc,
                currencyCode: state.currencyCode,
                showSomeDetail: state.showSomeDetail,
                showMoreDetail: state.showMoreDetail,
                selectedLine: state.selectedLine,
                walletLines: state.walletLines,
                isTaxIncluded: state.isTaxIncluded,
                isPeriodical: state.isPeriodical,
                showLineSheet: state.showLineSheet,
                showCategorySheet: state.showCategorySheet,
                showPeriodicalSheet: state.showPeriodicalSheet,
                showDateSheet: state.showDateSheet,
                selectedCategories: state.selectedCategories,
                categories: state.categories,
                selectedInitialDate: state.selectedInitialDate,
                selectedFrequency: state.selectedFrequency,
                selectedCustomFrequency: state.selectedCustomFrequency,
                selectedStartDate: state.selectedStartDate,
                selectedEndDate: selectedEndDate,
                selectedDuration: state.selectedDuration,
                selectedDurationTimes: state.selectedDurationTimes
            )
        }
    }

    private func updateSelectedStartDate(selectedStartDate: Date?) {
        DispatchQueue.main.async { [self] in
            state = TransactionState(
                isDepositSelected: state.isDepositSelected,
                amount: state.amount,
                title: state.title,
                desc: state.desc,
                currencyCode: state.currencyCode,
                showSomeDetail: state.showSomeDetail,
                showMoreDetail: state.showMoreDetail,
                selectedLine: state.selectedLine,
                walletLines: state.walletLines,
                isTaxIncluded: state.isTaxIncluded,
                isPeriodical: state.isPeriodical,
                showLineSheet: state.showLineSheet,
                showCategorySheet: state.showCategorySheet,
                showPeriodicalSheet: state.showPeriodicalSheet,
                showDateSheet: state.showDateSheet,
                selectedCategories: state.selectedCategories,
                categories: state.categories,
                selectedInitialDate: state.selectedInitialDate,
                selectedFrequency: state.selectedFrequency,
                selectedCustomFrequency: state.selectedCustomFrequency,
                selectedStartDate: selectedStartDate,
                selectedEndDate: state.selectedEndDate,
                selectedDuration: state.selectedDuration,
                selectedDurationTimes: state.selectedDurationTimes
            )
        }
    }

    private func updateSelectedFrequency(selectedFrequency: Frequency?) {
        DispatchQueue.main.async { [self] in
            state = TransactionState(
                isDepositSelected: state.isDepositSelected,
                amount: state.amount,
                title: state.title,
                desc: state.desc,
                currencyCode: state.currencyCode,
                showSomeDetail: state.showSomeDetail,
                showMoreDetail: state.showMoreDetail,
                selectedLine: state.selectedLine,
                walletLines: state.walletLines,
                isTaxIncluded: state.isTaxIncluded,
                isPeriodical: state.isPeriodical,
                showLineSheet: state.showLineSheet,
                showCategorySheet: state.showCategorySheet,
                showPeriodicalSheet: state.showPeriodicalSheet,
                showDateSheet: state.showDateSheet,
                selectedCategories: state.selectedCategories,
                categories: state.categories,
                selectedInitialDate: state.selectedInitialDate,
                selectedFrequency: selectedFrequency,
                selectedCustomFrequency: state.selectedCustomFrequency,
                selectedStartDate: state.selectedStartDate,
                selectedEndDate: state.selectedEndDate,
                selectedDuration: state.selectedDuration,
                selectedDurationTimes: state.selectedDurationTimes
            )
        }
    }

    private func updateSelectedLine(selectedLine: WalletLineUiItem?) {
        DispatchQueue.main.async { [self] in
            state = TransactionState(
                isDepositSelected: state.isDepositSelected,
                amount: state.amount,
                title: state.title,
                desc: state.desc,
                currencyCode: state.currencyCode,
                showSomeDetail: state.showSomeDetail,
                showMoreDetail: state.showMoreDetail,
                selectedLine: selectedLine,
                walletLines: state.walletLines,
                isTaxIncluded: state.isTaxIncluded,
                isPeriodical: state.isPeriodical,
                showLineSheet: state.showLineSheet,
                showCategorySheet: state.showCategorySheet,
                showPeriodicalSheet: state.showPeriodicalSheet,
                showDateSheet: state.showDateSheet,
                selectedCategories: state.selectedCategories,
                categories: state.categories,
                selectedInitialDate: state.selectedInitialDate,
                selectedFrequency: state.selectedFrequency,
                selectedCustomFrequency: state.selectedCustomFrequency,
                selectedStartDate: state.selectedStartDate,
                selectedEndDate: state.selectedEndDate,
                selectedDuration: state.selectedDuration,
                selectedDurationTimes: state.selectedDurationTimes
            )
        }
    }

    private func updateInitDate(selectedInitialDate: Date?) {
        DispatchQueue.main.async { [self] in
            state = TransactionState(
                isDepositSelected: state.isDepositSelected,
                amount: state.amount,
                title: state.title,
                desc: state.desc,
                currencyCode: state.currencyCode,
                showSomeDetail: state.showSomeDetail,
                showMoreDetail: state.showMoreDetail,
                selectedLine: state.selectedLine,
                walletLines: state.walletLines,
                isTaxIncluded: state.isTaxIncluded,
                isPeriodical: state.isPeriodical,
                showLineSheet: state.showLineSheet,
                showCategorySheet: state.showCategorySheet,
                showPeriodicalSheet: state.showPeriodicalSheet,
                showDateSheet: state.showDateSheet,
                selectedCategories: state.selectedCategories,
                categories: state.categories,
                selectedInitialDate: selectedInitialDate,
                selectedFrequency: state.selectedFrequency,
                selectedCustomFrequency: state.selectedCustomFrequency,
                selectedStartDate: state.selectedStartDate,
                selectedEndDate: state.selectedEndDate,
                selectedDuration: state.selectedDuration,
                selectedDurationTimes: state.selectedDurationTimes
            )
        }
    }

    private func updateState(
        isDepositSelected: Bool? = nil,
        amount: String? = nil,
        title: String? = nil,
        desc: String? = nil,
        currencyCode: String? = nil,
        showSomeDetail: Bool? = nil,
        showMoreDetail: Bool? = nil,
        selectedLine: WalletLineUiItem? = nil,
        walletLines: [WalletLineUiItem]? = nil,
        isTaxIncluded: Bool? = nil,
        isPeriodical: Bool? = nil,
        showLineSheet: Bool? = nil,
        showCategorySheet: Bool? = nil,
        showPeriodicalSheet: Bool? = nil,
        showDateSheet: Bool? = nil,
        selectedCategories: Set<String>? = nil,
        categories: [String]? = nil,
        selectedInitialDate: Date? = nil,
        selectedFrequency: Frequency? = nil,
        selectedCustomFrequency: [DayFrequency]? = nil,
        selectedStartDate: Date? = nil,
        selectedEndDate: Date? = nil,
        selectedDuration: Duration? = nil,
        selectedDurationTimes: Int? = nil
    ) {
        DispatchQueue.main.async { [self] in
            state = TransactionState(
                isDepositSelected: isDepositSelected ?? state.isDepositSelected,
                amount: amount ?? state.amount,
                title: title ?? state.title,
                desc: desc ?? state.desc,
                currencyCode: currencyCode ?? state.currencyCode,
                showSomeDetail: showSomeDetail ?? state.showSomeDetail,
                showMoreDetail: showMoreDetail ?? state.showMoreDetail,
                selectedLine: selectedLine ?? state.selectedLine,
                walletLines: walletLines ?? state.walletLines,
                isTaxIncluded: isTaxIncluded ?? state.isTaxIncluded,
                isPeriodical: isPeriodical ?? state.isPeriodical,
                showLineSheet: showLineSheet ?? state.showLineSheet,
                showCategorySheet: showCategorySheet ?? state.showCategorySheet,
                showPeriodicalSheet: showPeriodicalSheet ?? state.showPeriodicalSheet,
                showDateSheet: showDateSheet ?? state.showDateSheet,
                selectedCategories: selectedCategories ?? state.selectedCategories,
                categories: categories ?? state.categories,
                selectedInitialDate: selectedInitialDate ?? state.selectedInitialDate,
                selectedFrequency: selectedFrequency ?? state.selectedFrequency,
                selectedCustomFrequency: selectedCustomFrequency ?? state.selectedCustomFrequency,
                selectedStartDate: selectedStartDate ?? state.selectedStartDate,
                selectedEndDate: selectedEndDate ?? state.selectedEndDate,
                selectedDuration: selectedDuration ?? state.selectedDuration,
                selectedDurationTimes: selectedDurationTimes ?? state.selectedDurationTimes
            )
        }
    }
}

enum TransactionEvent {
    case ChangeIsDeposit(isDeposit: Bool)
    case ChangeTitle(title: String)
    case ChangeAmount(amount: String)
    case ChangeDescription(desc: String)
    case ChangeLine(line: WalletLineUiItem?)
    case ChangeInitDate(date: Date?)
    case ChangeIsTax(isTax: Bool)
    case ChangeIsPeriodical(isPeriodical: Bool)
    case ChangeFrequency(frequency: Frequency?)
    case ChangeDayFreq(frequencies: [DayFrequency])
    case ChangeCategories(categories: Set<String>)
    case ChangePeriodicalStartDate(date: Date?)
    case ChangePeriodicalEndDate(date: Date?)
    case ChangeDuration(duration: Duration?)
    case ChangeDurationTimes(times: Int)
    case ShowSomeDetial(show: Bool)
    case ShowMoreDetial(show: Bool)
    case ShowLineSheet(show: Bool)
    case ShowPeriodicalSheet(show: Bool)
    case ShowCategorySheet(show: Bool)
    case ShowDateSheet(show: Bool)
    case CancelLineSelection
    case CancelCategories
    case CancelPeriodical
    case CancelDate
    case SaveClicked
}

enum TransactionEffect {
    case NavigateToWalletScreen
}

struct TransactionState {
    var isDepositSelected: Bool = false
    var amount: String = ""
    var title: String = ""
    var desc: String = ""
    var currencyCode: String = "USD"
    var showSomeDetail: Bool = false
    var showMoreDetail: Bool = false
    var selectedLine: WalletLineUiItem? = nil
    var walletLines: [WalletLineUiItem] = [
        WalletLineUiItem(
            id: "1",
            title: "Savings",
            percentage: 20,
            balance: 560.00,
            description: nil,
            categories: []
        )
    ]
    var isTaxIncluded: Bool = false
    var isPeriodical: Bool = false
    var showLineSheet: Bool = false
    var showCategorySheet: Bool = false
    var showPeriodicalSheet: Bool = false
    var showDateSheet: Bool = false
    var selectedCategories: Set<String> = []
    var categories: [String] = ["Gym", "Gym2", "Gym4", "Gymsda", "Gym5", "Gym6", "Gym7"]
    var selectedInitialDate: Date? = nil
    var selectedFrequency: Frequency? = nil
    var selectedCustomFrequency: [DayFrequency] = []
    var selectedStartDate: Date? = nil
    var selectedEndDate: Date? = nil
    var selectedDuration: Duration? = nil
    var selectedDurationTimes: Int = 1
}
