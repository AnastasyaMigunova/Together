package com.together.data.api

import com.together.data.models.TokenUserResponse
import com.together.data.models.auth.AuthRequestInfo
import com.together.data.models.courses.CourseRequestDTO
import com.together.data.models.courses.CourseResponseDTO
import com.together.data.models.courses.GetCoursesDTO
import com.together.data.models.notes.GetNotesDTO
import com.together.data.models.profile.GetProfileDTO
import com.together.data.models.profile.ProfileDTO
import com.together.data.models.registration.RegisterRequestInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth")
    suspend fun authUser(@Body authRequestInfo: AuthRequestInfo): TokenUserResponse

    @POST("register")
    suspend fun registerUser(@Body registerRequestInfo: RegisterRequestInfo): TokenUserResponse

    @GET("courses")
    suspend fun getCourses() : GetCoursesDTO

    @POST("courses")
    suspend fun postCourse(@Body courseRequestDTO: CourseRequestDTO) : CourseResponseDTO

    @GET("courses/{courseId}")
    suspend fun getCourseById(@Path(COURSE_ID) courseId: String) : CourseResponseDTO

    @GET("community_notes")
    suspend fun getNotes() : GetNotesDTO

//    @POST("community_notes")
//    suspend fun postNote(@Body request: PostNoteRequest): SingleNoteResponse
//
//    @POST("community_notes/comment/{noteId}")
//    suspend fun commentNote(
//        @Path(NOTE_ID) noteId: String,
//        @Body request: CommentNoteRequest
//    ): SingleNoteResponse
//
//    @GET("chat")
//    suspend fun getChat(): ChatResponse
//
//    @POST("chat")
//    suspend fun sendChatMessage(@Body request: ChatRequest): ChatResponse
//
    @GET("profile")
    suspend fun getProfile(): GetProfileDTO
//
//    @GET("profile/{userId}")
//    suspend fun getProfileById(
//        @Path(USER_ID) userId: String
//    ): SingleProfileDTO
//
//    @GET("profile/all")
//    suspend fun getAllProfiles(): ProfilesResponse
//
//    @POST("profile/role")
//    suspend fun changeRole(@Body request: ChangeRoleRequest): SingleProfileResponse
//
//    @POST("profile/phone_visibility")
//    suspend fun changePhoneVisibility(@Body request: PhoneVisibilityRequest): SingleProfileResponse

    companion object {
        private const val COURSE_ID = "courseId"
        private const val NOTE_ID = "noteId"
        private const val USER_ID = "userId"
    }
}