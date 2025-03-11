package com.together.domain.usecase.courses.get_courses

import android.util.Log
import com.together.data.repository.courses.FakeCourseRepositoryImpl
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.CourseRepository
import com.together.ui.models.CourseVO
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository = FakeCourseRepositoryImpl(),
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getCourses(): List<CourseVO> {
        return courseRepository.getCourses()
            .fold(
                onSuccess = { courses -> courses.map { domainToUiMapper.run { it.toViewObject() } } },
                onFailure = { error ->
                    Log.e("GetCoursesUseCase", "Ошибка при загрузке курсов: ${error.message}")
                    throw error
                }
            )
    }
}