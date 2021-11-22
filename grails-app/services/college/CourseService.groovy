package college

import grails.transaction.Transactional

@Transactional
class CourseService {

    def getAllCourses() {
        def courses = Course.list()
        return courses
    }

    def getCourseById(def id) {
        def course = Course.findById(id)

        if (!course) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }

        return course
    }

    def getStudents(def id) {
        def course = Course.findById(id)

        if (!course) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        return course.students
    }

    def createCourse(Course course) {
        if (!course.save()) {
            println "Unable to create course beacuse of ${course.errors.allErrors.defaultMessage.join(',')}"
            return [
                    status : 'error',
                    message: 'Unable to create course'
            ]
        }
        return [
                status : 'success',
                message: "Course created with name: ${course.name}"
        ]
    }

    def updateCourse(Course course) {
        if (!course) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        if (!course.save(flush: true)) {
            println "Unable to update course beacuse of ${course.errors.allErrors.defaultMessage.join(',')}"
            return [
                    status : 'error',
                    message: 'Unable to update course'
            ]
        }
        return [
                status : 'success',
                message: "Course updated with name: ${course.name}"
        ]
    }

    def deleteCourse(def id) {
        def courses = Course.findById(id)

        if (!courses) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        courses.delete(flush: true)

        return [
                status : 'success',
                message: 'course deleted'
        ]
    }

    def registerStudent(def courseId, def studentId) {
        return this.registration(courseId, studentId, true)
    }

    def unregisterStudent(def courseId, def studentId) {
        return this.registration(courseId, studentId, false)
    }

    def registration(def courseId, def studentId, def register = true) {
        def course = Course.findById(courseId)
        def student = Student.findById(studentId)
        if (!course || !student) {
            return [
                    status : 'error',
                    message: 'Please provide valid course and student id'
            ]
        }
        if (register) {
            course.addToStudents(student)
        } else {
            course.removeFromStudents(student)
        }
        if (!course.save(flush: true)) {
            println("register error:${course.errors}")
            return [
                    status : 'error',
                    message: 'Unable to register student'
            ]
        }
        return [
                status : 'success',
                message: "Student ${student.name} ${register ? 'registered ' : 'unregistered '}for course ${course.name}"
        ]
    }
}
