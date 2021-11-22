package college

import grails.converters.JSON

class DepartmentController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def departmentService
    def validationService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def departments = departmentService.getAllDepartments()
        render departments as JSON
    }

    def show() {
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        result = departmentService.getDepartmentById(id)
        render result as JSON

    }

    def students() {
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        def students = departmentService.getStudents(id)
        render students as JSON
    }

    def create(Department departments) {
        def result = departmentService.createDepartment(departments)
        render result as JSON
    }

    def update(Department departments) {
        def result = departmentService.updateDepartment(departments)
        render result as JSON
    }

    def delete() {
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        result = departmentService.deleteDepartment(id)
        render result as JSON
    }
}
