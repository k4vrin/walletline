//
//  AccountSummaryCard.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 6/4/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AccountSummaryCard: View {
    @State private var offset = CGSize.zero
    
    @State var wallet: WalletUiItem
    @State var blurBalance: Bool
    
    var isDragEnable: Bool = false
    var onDismiss: () -> Void
    
    var accLineString: String {
        var str = ""
        for (index, item) in wallet.lines.enumerated() {
            str.append(String(item.percentage))
            if wallet.lines.count - 1 > index {
                str.append(" | ")
            }
        }
        
        return str
    }
    
    var body: some View {
        ZStack {
            VStack {
                HStack {
                    HStack {
                        Image("ic_user_acc")
                        Text(wallet.title)
                            .headlineLargeStyle()
                            .foregroundColor(.neutralColor)
                    }
                    Spacer()
                    Button {
                        blurBalance.toggle()
                    } label: {
                        ZStack {
                            Circle()
                                .fill(Color.neutralColor.opacity(0.05))
                                .frame(width: 44, height: 44)
                                .overlay {
                                    Circle()
                                        .stroke(Color.neutralColor.opacity(0.08))
                                }
                            Image("ic_eye")
                                .renderingMode(.template)
                                .foregroundColor(.neutralColor)
                        }
                    }
                }
                
                Spacer()
                
                HStack {
                    VStack(alignment: .leading) {
                        Text(
                            NSLocalizedString("Account Balance", comment: "")
                        )
                        .titleSmallStyle()
                        .foregroundColor(.neutralColorShade2)
                        
                        CurrencyText(amount: wallet.balance)
                            .blur(radius: blurBalance ? 7 : 0)
                    }
                    
                    Spacer()
                    
                    VStack(alignment: .leading, spacing: Padding.small) {
                        Text(
                            NSLocalizedString("Current Lines", comment: "")
                        )
                        .titleSmallStyle()
                        .foregroundColor(.neutralColorShade2)
                        
                        Text(accLineString)
                            .bodyLargeStyle()
                            .foregroundColor(.neutralColor)
                    }
                }
            }
            .padding(.all, Padding.extraMedium)
        }
        .background {
            GeometryReader { geo in
                RoundedRectangle(cornerRadius: 16)
                    .fill(Color.mainColor)
                    .frame(width: geo.size.width, height: geo.size.height)
                
                Circle()
                    .frame(
                        width: geo.size.width,
                        height: geo.size.width
                    )
                    .offset(
                        x: -geo.size.width / 3,
                        y: geo.size.width / 6
                    )
                    .foregroundColor(.neutralColor)
                    .opacity(0.04)
                
                Circle()
                    .frame(
                        width: geo.size.width,
                        height: geo.size.width
                    )
                    .offset(
                        x: -geo.size.width / 1.3,
                        y: -geo.size.width / 4
                    )
                    .foregroundColor(.neutralColor)
                    .opacity(0.04)
            }
        }
        .clipped()
        .offset(CGSize(width: offset.width, height: 0))
        .gesture(
            DragGesture()
                .onChanged { gesture in
                    if isDragEnable {
                        offset = gesture.translation
                    }
                }
                .onEnded { _ in
                    let isSwiped = withAnimation {
                        swipeCard(width: offset.width)
                    }
                    if isSwiped {
                        DispatchQueue.main.asyncAfter(deadline: .now() + 0.2) {
                            withAnimation(.default) {
                                onDismiss()
                                offset = CGSize(width: 0, height: 0)
                            }
                        }
                    }
                }
        )
        .onChange(of: isDragEnable) { isEnable in
            if !isEnable {
                offset = .zero
            }
        }
    }
    
    func swipeCard(width: CGFloat) -> Bool {
        switch width {
        case -500...(-200):
            offset = CGSize(width: -500, height: 0)
            return true
        case 200...500:
            offset = CGSize(width: 500, height: 0)
            return true
        default:
            offset = .zero
            return false
        }
    }
}

struct AccountSummaryCard_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            AccountSummaryCard(
                wallet: WalletUiItem(id: UUID().uuidString, title: "Wallet", balance: 5465.45, description: nil, lines: [
                    WalletLineUiItem(
                        id: UUID().uuidString,
                        title: "Needs",
                        percentage: 50,
                        balance: 560.00,
                        description: nil,
                        categories: []
                    ),
                    WalletLineUiItem(
                        id: UUID().uuidString,
                        title: "Wants",
                        percentage: 30,
                        balance: 560.00,
                        description: nil,
                        categories: []
                    ),
                    WalletLineUiItem(
                        id: UUID().uuidString,
                        title: "Saves",
                        percentage: 20,
                        balance: 560.00,
                        description: nil,
                        categories: []
                    )
                ], transactions: []), blurBalance: false
            ) {}
                .frame(height: 184)
                .padding()
        }
    }
}
