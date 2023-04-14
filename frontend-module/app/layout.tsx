import React from "react";

export const metadata = {
    title: 'MOBA Tracker',
}

interface RootLayoutProps {
    children: React.ReactNode
}

export default function RootLayout({children}: RootLayoutProps) {
    return (
        <html lang="de">
        <body>{children}</body>
        </html>
    )
}
