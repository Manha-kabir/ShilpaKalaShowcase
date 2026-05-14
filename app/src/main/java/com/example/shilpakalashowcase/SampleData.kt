package com.example.shilpakalashowcase

object SampleData {

    // Indian temple/sculpture images from Unsplash (free, no key needed)
    private const val S1 = "https://images.unsplash.com/photo-1582483648646-2e5dcd67f746?w=600"
    private const val S2 = "https://images.unsplash.com/photo-1524492412937-b28074a5d7da?w=600"
    private const val S3 = "https://images.unsplash.com/photo-1570168007204-dfb528c6958f?w=600"
    private const val S4 = "https://images.unsplash.com/photo-1601000938259-6e1b8b4c3c1e?w=600"
    private const val S5 = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=600"
    private const val S6 = "https://images.unsplash.com/photo-1545569341-9eb8b30979d9?w=600"
    private const val S7 = "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=600"
    private const val S8 = "https://images.unsplash.com/photo-1564507592333-c60657eea523?w=600"
    private const val A1 = "https://images.unsplash.com/photo-1530268729831-4b0b9e170218?w=600"
    private const val A2 = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=600"
    private const val A3 = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=600"

    val artists = listOf(
        Artist(
            id = "artist_001",
            name = "Raju Shilpi",
            location = "Shivarapatna, Karnataka",
            specialty = "Hoysala Stone Carving",
            bio = "Third generation stone carver with 25 years of experience.",
            profileImageUrl = A1,
            sculptures = listOf(
                Sculpture("SKS-001", "Ganesha — Hoysala Style", "Black Granite", "18 x 12 x 8 in", "₹45,000", S1),
                Sculpture("SKS-002", "Nandi Bull", "Soapstone", "24 x 14 x 10 in", "₹60,000", S2),
                Sculpture("SKS-003", "Lakshmi Panel", "Black Granite", "30 x 18 in", "₹75,000", S3),
                Sculpture(
                    productId = "SKS-004",
                    title = "Dancing Shiva",
                    material = "White Marble",
                    dimensions = "20 x 10 x 8 in",
                    price = "₹1,20,000",
                    imageUrl = S4,
                    isWorkInProgress = true,
                    wipStages = listOf(
                        WipStage(1, "Raw granite block selected", S1),
                        WipStage(2, "Rough outline chiseled", S2),
                        WipStage(3, "Main form carved", S3),
                        WipStage(4, "Detail work in progress", S4),
                        WipStage(5, "Final polish applied", S5)
                    )
                ),
                Sculpture("SKS-005", "Saraswati Idol", "Soapstone", "16 x 8 x 6 in", "₹35,000", S5),
                Sculpture("SKS-006", "Elephant Pair", "Black Granite", "12 x 20 x 8 in", "₹55,000", S6)
            )
        ),
        Artist(
            id = "artist_002",
            name = "Venkatesh Acharya",
            location = "Belur, Karnataka",
            specialty = "Chalukya Wood Carving",
            bio = "Master craftsman specialising in intricate wood panel carvings.",
            profileImageUrl = A2,
            sculptures = listOf(
                Sculpture("SKS-007", "Temple Door Panel", "Rosewood", "48 x 24 in", "₹2,50,000", S7),
                Sculpture("SKS-008", "Dashavatara Frieze", "Teak Wood", "60 x 12 in", "₹1,80,000", S8),
                Sculpture("SKS-009", "Garuda Panel", "Rosewood", "24 x 18 in", "₹95,000", S1),
                Sculpture("SKS-010", "Peacock Medallion", "Sandalwood", "18 x 18 in", "₹1,10,000", S2),
                Sculpture("SKS-011", "Lotus Ceiling Panel", "Teak Wood", "36 x 36 in", "₹3,20,000", S3),
                Sculpture("SKS-012", "Krishna Flute", "Rosewood", "20 x 10 x 6 in", "₹70,000", S4)
            )
        ),
        Artist(
            id = "artist_003",
            name = "Manjunath Shilpi",
            location = "Halebidu, Karnataka",
            specialty = "Dravidian Stone Carving",
            bio = "Award-winning sculptor known for temple-grade carvings.",
            profileImageUrl = A3,
            sculptures = listOf(
                Sculpture("SKS-013", "Shiva Linga", "Black Granite", "14 x 8 in", "₹40,000", S5),
                Sculpture("SKS-014", "Murugan Vel", "White Marble", "36 x 8 x 6 in", "₹85,000", S6),
                Sculpture("SKS-015", "Navagraha Panel", "Black Granite", "48 x 24 in", "₹1,50,000", S7),
                Sculpture("SKS-016", "Apsara Dancer", "Soapstone", "22 x 8 x 6 in", "₹65,000", S8),
                Sculpture("SKS-017", "Bull Temple Guardian", "Black Granite", "30 x 20 x 15 in", "₹2,00,000", S1),
                Sculpture("SKS-018", "Hanuman Idol", "Red Sandstone", "24 x 10 x 8 in", "₹90,000", S2),
                Sculpture("SKS-019", "Kalasha Finial", "Granite", "18 x 10 in", "₹30,000", S3),
                Sculpture("SKS-020", "Dvarapala Guardian", "Black Granite", "48 x 18 x 12 in", "₹4,50,000", S4)
            )
        )
    )
}