package com.example.hedgehoglabapp

import com.example.hedgehoglabapp.model.pexelsitems.PexelsImage
import com.example.hedgehoglabapp.model.pexelsitems.PexelsResponse
import com.example.hedgehoglabapp.model.pexelsitems.SizeImages
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import org.junit.Test

class ResponseTest {

    @Test
    fun `ensure sample json passes to data response object`() {
        // arrange
        val expected = expectedDataResponse

        // act
        val actual = Gson().fromJson(actualDataResponseJson, PexelsResponse::class.java)

        // assert
        assertEquals(expected, actual)
    }

    private val expectedDataResponse = PexelsResponse(
        pageNumber = 1,
        pageLimit = "3",
        images = listOf(
            PexelsImage(
                id = 11543979,
                url = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
                creator = "ph.galtri",
                imageSizeList = SizeImages(
                    largeSize = "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                ),
                description = "Woman in Black Button Up Shirt"
            ),
            PexelsImage(
                id = 11543979,
                url = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
                creator = "ph.galtri",
                imageSizeList = SizeImages(
                    largeSize = "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                ),
                description = "Woman in Black Button Up Shirt"
            ),
            PexelsImage(
                id = 11543979,
                url = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
                creator = "ph.galtri",
                imageSizeList = SizeImages(
                    largeSize = "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                ),
                description = "Woman in Black Button Up Shirt"
            )
        ),
        nextPage = "https://api.pexels.com/v1/curated/?page=2&per_page=3"
    )
    private val actualDataResponseJson = """
        {
    "page": 1,
    "per_page": 3,
    "photos": [
        {
            "id": 11543979,
            "width": 4016,
            "height": 6016,
            "url": "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
            "photographer": "ph.galtri",
            "photographer_url": "https://www.pexels.com/@ph-galtri-122917742",
            "photographer_id": 122917742,
            "avg_color": "#10202D",
            "src": {
                "original": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg",
                "large2x": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "large": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                "medium": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=350",
                "small": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=130",
                "portrait": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
                "landscape": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
                "tiny": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            },
            "liked": false,
            "alt": "Woman in Black Button Up Shirt"
        },
        {
            "id": 11543979,
            "width": 4016,
            "height": 6016,
            "url": "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
            "photographer": "ph.galtri",
            "photographer_url": "https://www.pexels.com/@ph-galtri-122917742",
            "photographer_id": 122917742,
            "avg_color": "#10202D",
            "src": {
                "original": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg",
                "large2x": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "large": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                "medium": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=350",
                "small": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=130",
                "portrait": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
                "landscape": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
                "tiny": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            },
            "liked": false,
            "alt": "Woman in Black Button Up Shirt"
        },
        {
            "id": 11543979,
            "width": 4016,
            "height": 6016,
            "url": "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
            "photographer": "ph.galtri",
            "photographer_url": "https://www.pexels.com/@ph-galtri-122917742",
            "photographer_id": 122917742,
            "avg_color": "#10202D",
            "src": {
                "original": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg",
                "large2x": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                "large": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                "medium": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=350",
                "small": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&h=130",
                "portrait": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
                "landscape": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
                "tiny": "https://images.pexels.com/photos/11543979/pexels-photo-11543979.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            },
            "liked": false,
            "alt": "Woman in Black Button Up Shirt"
        }
    ],
    "next_page": "https://api.pexels.com/v1/curated/?page=2&per_page=3"
}
    """.trimIndent()
}