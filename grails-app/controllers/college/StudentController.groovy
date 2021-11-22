package college

import grails.converters.JSON

class StudentController {

    def studentService
    def validationService

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def students = studentService.getAllStudents()
        render students as JSON
    }

    def show() {
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        result = studentService.getStudentById(id)
        render result as JSON

    }

    def create(Student student) {
        def result = studentService.createStudent(student)
        render result as JSON
    }

    def update(Student student) {
        def result = studentService.updateStudent(student)
        render result as JSON
    }

    def delete() {
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        result = studentService.deleteStudent(id)
        render result as JSON
    }
}
