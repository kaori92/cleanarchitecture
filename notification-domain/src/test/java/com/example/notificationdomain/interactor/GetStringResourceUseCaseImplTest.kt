package com.example.notificationdomain.interactor

import com.example.notificationdomain.interactor.GetStringResourceNotificationUseCaseImpl
import com.example.cleanarchitecture.string.StringService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class GetStringResourceUseCaseImplTest : Spek({

    val stringService by memoized { mock<StringService>()}

    val useCaseImpl by memoized {
        GetStringResourceNotificationUseCaseImpl(stringService)
    }

    describe("execute"){
        val id = 1

        context("when execute is called"){

            beforeEachTest {
                useCaseImpl.execute(id)
            }

            it("should call stringService getStringResource"){
                verify(stringService).getStringResource(id)
            }
        }
    }

})