package college

import grails.converters.JSON

class StudentController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render Student.list(params) as JSON
    }

    def show() {
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def student = Student.findById(id)

        if (!student) {
            render 'Invalid Id'
            return
        }
        render student as JSON

    }

    def create(Student student) {
        if (!student.save()) {
            render "Unable to create student beacuse of ${student.errors.allErrors.defaultMessage.join(',')}"
            return
        }
        render "Student created with name: ${student.name}"
    }

    def update(Student student) {
        println("student")
        if(!student){
            render 'Please provide valid id'
            return
        }
        if (!student.save(flush: true)) {
            render "Unable to update student beacuse of ${student.errors.allErrors.defaultMessage.join(',')}"
            return
        }
        render "Student updated with name: ${student.id},${student.name}"
    }

    def delete() {
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def student = Student.findById(id)

        if (!student) {
            render 'Invalid Id'
            return
        }
        student.delete(flush: true)
        render 'student deleted'
    }
}
