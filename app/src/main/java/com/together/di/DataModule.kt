package com.together.di

import android.content.Context
import com.together.data.api.ApiService
import com.together.data.mapper.DataToDomainMapper
import com.together.data.mapper.DomainToDataMapper
import com.together.data.repository.auth.AuthRepositoryImpl
import com.together.data.repository.courses.CourseRepositoryImpl
import com.together.data.repository.notes.NoteRepositoryImpl
import com.together.data.repository.profile.ProfileRepositoryImpl
import com.together.data.repository.auth.FakeAuthRepositoryImpl
import com.together.data.repository.register.RegisterRepositoryImpl
import com.together.data.repository.courses.FakeCourseRepositoryImpl
import com.together.data.repository.notes.FakeNoteRepositoryImpl
import com.together.data.repository.profile.FakeProfileRepositoryImpl
import com.together.data.repository.register.FakeRegisterRepositoryImpl
import com.together.data.storage.preferences.PreferencesManager
import com.together.data.storage.preferences.TokenManager
import com.together.data.storage.room.dao.LocalNoteDao
import com.together.domain.repository.AuthRepository
import com.together.domain.repository.CourseRepository
import com.together.domain.repository.NoteRepository
import com.together.domain.repository.ProfileRepository
import com.together.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideAuthRepository(
        apiService: ApiService,
        tokenManager: TokenManager
    ): AuthRepository {
        return AuthRepositoryImpl(apiService, tokenManager)
    }

    @Provides
    fun provideFakeAuthRepository(
        tokenManager: TokenManager
    ): AuthRepository {
        return FakeAuthRepositoryImpl(tokenManager)
    }

    @Provides
    fun provideRegisterRepository(
        apiService: ApiService,
        tokenManager: TokenManager
    ): RegisterRepository {
        return RegisterRepositoryImpl(apiService, tokenManager)
    }

    @Provides
    fun provideFakeRegisterRepository(
        tokenManager: TokenManager
    ): RegisterRepository {
        return FakeRegisterRepositoryImpl(tokenManager)
    }

    @Provides
    fun provideCourseRepository(
        apiService: ApiService,
        dataToDomainMapper: DataToDomainMapper,
        domainToDataMapper: DomainToDataMapper
    ): CourseRepository {
        return CourseRepositoryImpl(apiService, dataToDomainMapper, domainToDataMapper)
    }

    @Provides
    fun provideFakeCourseRepository(): CourseRepository {
        return FakeCourseRepositoryImpl()
    }

    @Provides
    fun provideNoteRepository(
        apiService: ApiService,
        noteDao: LocalNoteDao,
        dataToDomainMapper: DataToDomainMapper,
        domainToDataMapper: DomainToDataMapper
    ): NoteRepository {
        return NoteRepositoryImpl(apiService, noteDao, dataToDomainMapper, domainToDataMapper)
    }

    @Provides
    fun provideFakeNoteRepository(
        noteDao: LocalNoteDao,
        dataToDomainMapper: DataToDomainMapper
    ): NoteRepository {
        return FakeNoteRepositoryImpl(noteDao, dataToDomainMapper)
    }

    @Provides
    fun provideProfileRepository(
        apiService: ApiService,
        dataToDomainMapper: DataToDomainMapper
    ): ProfileRepository {
        return ProfileRepositoryImpl(apiService, dataToDomainMapper)
    }

    @Provides
    fun provideFakeProfileRepository(): ProfileRepository {
        return FakeProfileRepositoryImpl()
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): PreferencesManager {
        return PreferencesManager(context)
    }

    @Provides
    fun provideTokenManager(preferencesManager: PreferencesManager): TokenManager {
        return TokenManager(preferencesManager)
    }
}