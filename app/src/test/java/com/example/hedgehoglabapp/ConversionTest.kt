package com.example.hedgehoglabapp

import com.example.hedgehoglabapp.model.pexelsitems.PexelsImage
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemEntity
import com.example.hedgehoglabapp.model.pexelsitems.PexelsImagesItemUiModel
import com.example.hedgehoglabapp.model.pexelsitems.SizeImages
import com.example.hedgehoglabapp.model.pexelsitems.convertToEntity
import com.example.hedgehoglabapp.model.pexelsitems.convertToUiModel
import org.junit.Assert
import org.junit.Test

class ConversionTest {

    @Test
    fun `ensure pexelsImage is converted to uimodel`() {
        // arrange
        val example = PexelsImage(
            id = 1,
            url = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
            creator = "Mark Markinson",
            imageSizeList = SizeImages(
                largeSize = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/"
            ),
            description = "some description"
        )
        val expected = PexelsImagesItemUiModel(
            imageId = 1,
            imageUrl = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
            imageCreator = "Mark Markinson",
            imageDescription = "some description"
        )

        // act
        val convertedExample = example.convertToUiModel()

        // assert
        Assert.assertEquals(convertedExample, expected)
    }

    @Test
    fun `ensure entity is converted to uimodel`() {
        // arrange
        val example = PexelsImagesItemEntity(
            imageId = 1,
            imageUrl = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
            imageCreator = "Mark Markinson",
            imageDescription = "some description"
        )
        val expected = PexelsImagesItemUiModel(
            imageId = 1,
            imageUrl = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
            imageCreator = "Mark Markinson",
            imageDescription = "some description"
        )

        // act
        val convertedExample = example.convertToUiModel()

        // assert
        Assert.assertEquals(convertedExample, expected)
    }

    @Test
    fun `ensure uimodel is converted to entity`() {
        // arrange
        val example = PexelsImagesItemUiModel(
            imageId = 1,
            imageUrl = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
            imageCreator = "Mark Markinson",
            imageDescription = "some description"
        )
        val expected = PexelsImagesItemEntity(
            imageId = 1,
            imageUrl = "https://www.pexels.com/photo/elegant-woman-in-black-dress-with-hands-clasped-11543979/",
            imageCreator = "Mark Markinson",
            imageDescription = "some description"
        )

        // act
        val convertedExample = example.convertToEntity()

        // assert
        Assert.assertEquals(convertedExample, expected)
    }
}