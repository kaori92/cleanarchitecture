package com.example.cleanarchitecture.string

import android.content.Context
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

class DefaultStringServiceTest : Spek({
    val context by memoized {
        mock<Context>()
    }

    val stringService by memoized {
        DefaultStringService(context)
    }

    val id = 1
    val message = "abc"
    var result = ""

    describe("getting string resource"){

        context("when calling getStringResource"){
            beforeEachTest {
                given(context.getString(id)).willReturn(message)

                result = stringService.getStringResource(id)
            }

            it("should call context getString"){
                assertEquals(message, result)
            }
        }

    }

})