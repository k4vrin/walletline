//
//  Extensions.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/24/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

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
