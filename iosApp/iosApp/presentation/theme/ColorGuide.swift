//
//  ColorGuide.swift
//  iosApp
//
//  Created by Mostafa Hosseini on 2/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ColorGuide: View {
    var body: some View {
        HStack {
            VStack {
                Capsule()
                    .fill(Color.mainColor)
                Capsule()
                    .fill(Color.mainColorShade1)
                Capsule()
                    .fill(Color.mainColorShade2)
                Capsule()
                    .fill(Color.mainColorShade3)
                Capsule()
                    .fill(Color.mainColorShade4)
                Capsule()
                    .fill(Color.mainColorShade5)
                Capsule()
                    .fill(Color.mainColorShade6)
            }
            
            VStack {
                Capsule()
                    .fill(Color.successColor)
                Capsule()
                    .fill(Color.successColorShade1)
                Capsule()
                    .fill(Color.successColorShade2)
                Capsule()
                    .fill(Color.successColorShade3)
                Capsule()
                    .fill(Color.successColorShade4)
                Capsule()
                    .fill(Color.successColorShade5)
                Capsule()
                    .fill(Color.successColorShade6)
            }
            
            VStack {
                Capsule()
                    .fill(Color.warningColor)
                Capsule()
                    .fill(Color.warningColorShade1)
                Capsule()
                    .fill(Color.warningColorShade2)
                Capsule()
                    .fill(Color.warningColorShade3)
                Capsule()
                    .fill(Color.warningColorShade4)
                Capsule()
                    .fill(Color.warningColorShade5)
                Capsule()
                    .fill(Color.warningColorShade6)
            }
            
            VStack {
                Capsule()
                    .fill(Color.errorColor)
                Capsule()
                    .fill(Color.errorColorShade1)
                Capsule()
                    .fill(Color.errorColorShade2)
                Capsule()
                    .fill(Color.errorColorShade3)
                Capsule()
                    .fill(Color.errorColorShade4)
                Capsule()
                    .fill(Color.errorColorShade5)
                Capsule()
                    .fill(Color.errorColorShade6)
            }
            
            VStack {
                Capsule()
                    .fill(Color.infoColor)
                Capsule()
                    .fill(Color.infoColorShade1)
                Capsule()
                    .fill(Color.infoColorShade2)
                Capsule()
                    .fill(Color.infoColorShade3)
                Capsule()
                    .fill(Color.infoColorShade4)
                Capsule()
                    .fill(Color.infoColorShade5)
                Capsule()
                    .fill(Color.infoColorShade6)
            }
            
            VStack {
                Capsule()
                    .fill(Color.neutralColor)
                Capsule()
                    .fill(Color.neutralColorShade1)
                Capsule()
                    .fill(Color.neutralColorShade2)
                Capsule()
                    .fill(Color.neutralColorShade3)
                Capsule()
                    .fill(Color.neutralColorShade4)
                Capsule()
                    .fill(Color.neutralColorShade5)
                Capsule()
                    .fill(Color.neutralColorDark)
            }
            
        }
    }
}

extension Color {
    
    public static let primaryColor: Color = Color("primary")
    public static let onPrimaryColor: Color = Color("onPrimary")
    public static let primaryContainerColor: Color = Color("primaryContainer")
    public static let onPrimaryContainerColor: Color = Color("onPrimaryContainer")
    
    public static let secondaryColor: Color = Color("secondary")
    public static let onSecondaryColor: Color = Color("onSecondary")
    public static let secondaryContainerColor: Color = Color("secondaryContainer")
    public static let onSecondaryContainerColor: Color = Color("onSecondaryContainer")
    
    public static let tertiaryColor: Color = Color("tertiary")
    public static let onTertiaryColor: Color = Color("onTertiary")
    public static let tertiaryContainerColor: Color = Color("tertiaryContainer")
    public static let onTertiaryContainerColor: Color = Color("onTertiaryContainer")
    
//    public static let errorColor: Color = Color("error")
    public static let onErrorColor: Color = Color("onError")
    public static let errorContainerColor: Color = Color("errorContainer")
    public static let onErrorContainerColor: Color = Color("onErrorContainer")
    
    public static let surfaceColor: Color = Color("surface")
    public static let onSurfaceColor: Color = Color("onSurface")
    public static let surfaceVariantColor: Color = Color("surfaceVariant")
    public static let onSurfaceVariantColor: Color = Color("onSurfaceVariant")
    
    public static let backgroundColor: Color = Color("background")
    public static let onBackgroundColor: Color = Color("onBackground")
    
    public static let outlineColor: Color = Color("outline")
    public static let outlineVariantColor: Color = Color("outlineVariant")
    
    public static let inverseSurfaceColor: Color = Color("inverseSurface")
    public static let inverseOnSurfaceColor: Color = Color("inverseOnSurface")
    
    public static let inversePrimaryColor: Color = Color("inversePrimary")
    
    
    public static let mainColor: Color = Color("mainColor")
    public static let mainColorShade1: Color = Color("mainColorShade1")
    public static let mainColorShade2: Color = Color("mainColorShade2")
    public static let mainColorShade3: Color = Color("mainColorShade3")
    public static let mainColorShade4: Color = Color("mainColorShade4")
    public static let mainColorShade5: Color = Color("mainColorShade5")
    public static let mainColorShade6: Color = Color("mainColorShade6")
    
    public static let successColor: Color = Color("successColor")
    public static let successColorShade1: Color = Color("successColorShade1")
    public static let successColorShade2: Color = Color("successColorShade2")
    public static let successColorShade3: Color = Color("successColorShade3")
    public static let successColorShade4: Color = Color("successColorShade4")
    public static let successColorShade5: Color = Color("successColorShade5")
    public static let successColorShade6: Color = Color("successColorShade6")
    
    public static let warningColor: Color = Color("warningColor")
    public static let warningColorShade1: Color = Color("warningColorShade1")
    public static let warningColorShade2: Color = Color("warningColorShade2")
    public static let warningColorShade3: Color = Color("warningColorShade3")
    public static let warningColorShade4: Color = Color("warningColorShade4")
    public static let warningColorShade5: Color = Color("warningColorShade5")
    public static let warningColorShade6: Color = Color("warningColorShade6")
    
    public static let errorColor: Color = Color("errorColor")
    public static let errorColorShade1: Color = Color("errorColorShade1")
    public static let errorColorShade2: Color = Color("errorColorShade2")
    public static let errorColorShade3: Color = Color("errorColorShade3")
    public static let errorColorShade4: Color = Color("errorColorShade4")
    public static let errorColorShade5: Color = Color("errorColorShade5")
    public static let errorColorShade6: Color = Color("errorColorShade6")
    
    public static let infoColor: Color = Color("infoColor")
    public static let infoColorShade1: Color = Color("infoColorShade1")
    public static let infoColorShade2: Color = Color("infoColorShade2")
    public static let infoColorShade3: Color = Color("infoColorShade3")
    public static let infoColorShade4: Color = Color("infoColorShade4")
    public static let infoColorShade5: Color = Color("infoColorShade5")
    public static let infoColorShade6: Color = Color("infoColorShade6")
    
    public static let neutralColor: Color = Color("neutralColor")
    public static let neutralColorShade1: Color = Color("neutralColorShade1")
    public static let neutralColorShade2: Color = Color("neutralColorShade2")
    public static let neutralColorShade3: Color = Color("neutralColorShade3")
    public static let neutralColorShade4: Color = Color("neutralColorShade4")
    public static let neutralColorShade5: Color = Color("neutralColorShade5")
    public static let neutralColorShade6: Color = Color("neutralColorShade6")
    public static let neutralColorDark: Color = Color("neutralColorDark")
}

struct ColorGuide_Previews: PreviewProvider {
    static var previews: some View {
        ColorGuide()
    }
}
