package college

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CourseController {

    def courseService
    def validationService

    static allowedMethods = [
            create: "POST",
            update: "PUT",
            delete: "DELETE",
            register: "POST",
            unregister: "POST"
    ]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def courses = courseService.getAllCourses()
        render courses as JSON
    }

    def show() {
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        result = courseService.getCourseById(id)
        render result as JSON
    }

    def create(Course course) {
        def result = courseService.createCourse(course)
        render result as JSON
    }

    def update(Course course) {
        def result = courseService.updateCourse(course)
        render result as JSON
    }

    def delete() {
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        result = courseService.deleteCourse(id)
        render result as JSON
    }

    def students(){
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        def students = courseService.getStudents(id)
        render students as JSON
    }

    def register(){
        def data = request.JSON
        def studentId = data.student
        def courseId = data.course

        def result = validationService.validateId(studentId, 'Student')
        if (result) {
            render result as JSON
            return
        }
        result = validationService.validateId(courseId, 'Course')
        if (result) {
            render result as JSON
            return
        }

        result = courseService.registerStudent(courseId, studentId)

        render result as JSON
    }

    def unregister(){
        def data = request.JSON
        def studentId = data.student
        def courseId = data.course
        def result = validationService.validateId(studentId, 'Student')
        if (result) {
            render result as JSON
            return
        }
        result = validationService.validateId(courseId, 'Course')
        if (result) {
            render result as JSON
            return
        }

        result = courseService.unregisterStudent(courseId, studentId)

        render result as JSON
    }
}
