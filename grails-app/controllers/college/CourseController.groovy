package college

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CourseController {

    static allowedMethods = [
            create: "POST",
            update: "PUT",
            delete: "DELETE",
            register: "POST",
            unregister: "POST"
    ]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render Course.list(params) as JSON
    }

    def show() {
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def course = Course.findById(id)

        if (!course) {
            render 'Invalid Id'
        } else {
            render course as JSON
        }
    }

    def create(Course course) {
        if (!course.save()) {
            render "Unable to create course beacuse of ${course.errors.allErrors.defaultMessage.join(',')}"
            return
        }
        render "Course created with name: ${course.name}"
    }

    def edit(Course courses) {
        respond courses
    }

    def update(Course course) {
        def courseIns = Course.findById(course.id)
        if (!courseIns) {
            render 'Invalid Id'
            return
        }
        bindData(courseIns, course)
        if (!courseIns.save(flush: true)) {
            render "Unable to update course beacuse of ${courseIns.errors.allErrors.defaultMessage.join(',')}"
            return
        }
        render "Course updated with name: ${courseIns.id},${courseIns.name}"
    }

    def delete() {
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def course = Course.findById(id)

        if (!course) {
            render 'Invalid Id'
            return
        }
        course.delete(flush: true)
        render 'Course deleted'
    }

    def students(){
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def course = Course.findById(id)

        if (!course) {
            render 'Invalid Id'
            return
        }
        render course.students as JSON
    }

    def register(){
        def data = request.JSON
        def studentId = data.student
        def courseId = data.course
        if(!courseId || !studentId){
            render 'Please provide student and course id'
            return
        }
        def course = Course.findById(courseId)
        def student = Student.findById(studentId)
        if(!course || !student){
            render 'Please provide valid student and course id'
            return
        }
        course.addToStudents(student)
        if(!course.save(flush: true)){
            println("register error:${course.errors}")
            render 'Unable to register student'
            return
        }
        render "Student ${student.name} registerd for course ${course.name}"
    }

    def unregister(){
        def data = request.JSON
        def studentId = data.student
        def courseId = data.course
        if(!courseId || !studentId){
            render 'Please provide student and course id'
            return
        }
        def course = Course.findById(courseId)
        def student = Student.findById(studentId)
        if(!course || !student){
            render 'Please provide valid student and course id'
            return
        }
        course.removeFromStudents(student)
        if(!course.save(flush: true)){
            println("register error:${course.errors}")
            render 'Unable to register student'
            return
        }
        render "Student ${student.name} unregisterd for course ${course.name}"
    }
}
