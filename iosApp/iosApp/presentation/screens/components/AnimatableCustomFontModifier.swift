//
//  AnimatableCustomFontModifier.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 4/16/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI


struct AnimatableCustomFontModifier: AnimatableModifier {
    
  var animatableData: CGFloat {
    get { size }
    set { size = newValue }
  }
  var size: CGFloat

  func body(content: Content) -> some View {
    content
      .font(.system(size: size))
  }

}

extension View {
  func animatableFont(size: CGFloat) -> some View {
    modifier(AnimatableCustomFontModifier(size: size))
  }
}
