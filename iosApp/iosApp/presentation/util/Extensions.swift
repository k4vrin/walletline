//
//  Extensions.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/24/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension Optional where Wrapped: Collection {
    func isNilOrEmpty() -> Bool {
        switch self {
        case let collection?:
            return collection.isEmpty
        case nil:
            return true
        }
    }
}

extension Decimal {
    var doubleValue:Double {
        return NSDecimalNumber(decimal:self).doubleValue
    }
}


extension Collection {
    /// Returns the element at the specified index if it is within bounds, otherwise nil.
    subscript (safe index: Index) -> Element? {
        return indices.contains(index) ? self[index] : nil
    }
}


extension View {
    func placeholder<Content: View>(
        when shouldShow: Bool,
        alignment: Alignment = .leading,
        @ViewBuilder placeholder: () -> Content) -> some View {

        ZStack(alignment: alignment) {
            placeholder().opacity(shouldShow ? 1 : 0)
            self
        }
    }
}


extension String {
    func isBlank() -> Bool {
        self.isEmpty || self.allSatisfy { ch in
            ch.isWhitespace
        }
    }
}
