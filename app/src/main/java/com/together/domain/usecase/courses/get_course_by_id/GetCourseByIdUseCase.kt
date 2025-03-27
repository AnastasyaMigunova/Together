package com.together.domain.usecase.courses.get_course_by_id

import android.util.Log
import com.together.domain.mapper.DomainToUiMapper
import com.together.domain.repository.CourseRepository
import com.together.ui.models.CourseVO
import javax.inject.Inject

class GetCourseByIdUseCase @Inject constructor(
    private val courseRepository: CourseRepository,
    private val domainToUiMapper: DomainToUiMapper
) {
    suspend fun getCourseById(courseId: String): CourseVO {
        return courseRepository.getCourseById(courseId)
            .fold(
                onSuccess = { course -> domainToUiMapper.run { course.toViewObject() } },
                onFailure = { error ->
                    Log.e("GetCourseByIdUseCase", "Ошибка при загрузке курса: ${error.message}")
                    throw error
                }
            )
    }
}
