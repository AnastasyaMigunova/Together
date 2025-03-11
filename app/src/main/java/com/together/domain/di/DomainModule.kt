package com.together.domain.di

import com.together.data.repository.auth.AuthRepositoryImpl
import com.together.data.repository.courses.CourseRepositoryImpl
import com.together.data.repository.notes.NoteRepositoryImpl
import com.together.data.repository.profile.ProfileRepositoryImpl
import com.together.data.repository.register.RegisterRepositoryImpl
import com.together.data.storage.preferences.TokenManager
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.mapper.UiToDomainMapper
import com.together.domain.usecase.auth.AuthUseCase
import com.together.domain.usecase.auth.CheckUserLoggedInUseCase
import com.together.domain.usecase.courses.get_course_by_id.GetCourseByIdUseCase
import com.together.domain.usecase.courses.get_courses.GetCoursesUseCase
import com.together.domain.usecase.courses.get_courses.GetLastCoursesUseCase
import com.together.domain.usecase.courses.post_course.PostCourseUseCase
import com.together.domain.usecase.notes.get_community_notes.GetCommunityLastNoteUseCase
import com.together.domain.usecase.notes.get_community_notes.GetCommunityNotesUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalFavNotesUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalLastNoteUseCase
import com.together.domain.usecase.notes.get_local_notes.GetLocalNotesUseCase
import com.together.domain.usecase.notes.post_local_note.PostLocalNoteUseCase
import com.together.domain.usecase.profile.GetProfileUseCase
import com.together.domain.usecase.registration.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideAuthUseCase(authRepositoryImpl: AuthRepositoryImpl): AuthUseCase {
        return AuthUseCase(authRepositoryImpl)
    }

    @Provides
    fun provideRegisterUseCase(registerRepositoryImpl: RegisterRepositoryImpl): RegisterUseCase {
        return RegisterUseCase(registerRepositoryImpl)
    }

    @Provides
    fun provideGetCoursesUseCase(
        courseRepositoryImpl: CourseRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetCoursesUseCase {
        return GetCoursesUseCase(courseRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun providePostCourseUseCase(
        courseRepositoryImpl: CourseRepositoryImpl,
        domainToUiMapper: DomainToUiMapper,
        uiToDomainMapper: UiToDomainMapper
    ) : PostCourseUseCase {
        return PostCourseUseCase(courseRepositoryImpl, uiToDomainMapper, domainToUiMapper)
    }

    @Provides
    fun provideGetCourseByIdUseCase(
        courseRepositoryImpl: CourseRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetCourseByIdUseCase {
        return GetCourseByIdUseCase(courseRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetLastCoursesUseCase(
        courseRepositoryImpl: CourseRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetLastCoursesUseCase {
        return GetLastCoursesUseCase(courseRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetCommunityNotesUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetCommunityNotesUseCase {
        return GetCommunityNotesUseCase(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetCommunityLastNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetCommunityLastNoteUseCase {
        return GetCommunityLastNoteUseCase(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetLocalNotesUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetLocalNotesUseCase {
        return GetLocalNotesUseCase(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetLocalLastNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetLocalLastNoteUseCase {
        return GetLocalLastNoteUseCase(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideGetLocalFavNotesUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ) : GetLocalFavNotesUseCase {
        return GetLocalFavNotesUseCase(noteRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun providePostLocalNoteUseCase(
        noteRepositoryImpl: NoteRepositoryImpl,
        uiToDomainMapper: UiToDomainMapper
    ) : PostLocalNoteUseCase {
        return PostLocalNoteUseCase(noteRepositoryImpl, uiToDomainMapper)
    }

    @Provides
    fun provideGetProfileUseCase(
        profileRepositoryImpl: ProfileRepositoryImpl,
        domainToUiMapper: DomainToUiMapper
    ): GetProfileUseCase {
        return GetProfileUseCase(profileRepositoryImpl, domainToUiMapper)
    }

    @Provides
    fun provideCheckUserLoggedInUseCase(tokenManager: TokenManager): CheckUserLoggedInUseCase {
        return CheckUserLoggedInUseCase(tokenManager)
    }
}