package college

import grails.converters.JSON

class DepartmentController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render Department.list(params) as JSON
    }

    def show() {
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def departments = Department.findById(id)

        if (!departments) {
            render 'Invalid Id'
            return
        }
        render departments as JSON

    }

    def students(){
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def departments = Department.findById(id)

        if (!departments) {
            render 'Invalid Id'
            return
        }
        render departments.students as JSON
    }

    def create(Department departments) {
        if (!departments.save()) {
            render "Unable to create department beacuse of ${departments.errors.allErrors.defaultMessage.join(',')}"
            return
        }
        render "Department created with name: ${departments.name}"
    }

    def update(Department departments) {
        def dept = Department.findById(departments.id)
        if (!departments) {
            render 'Invalid Id'
            return
        }
        bindData(dept, departments)
        if (!dept.save(flush: true)) {
            render "Unable to update department beacuse of ${dept.errors.allErrors.defaultMessage.join(',')}"
            return
        }
        render "Department updated with name: ${dept.id},${dept.name}"
    }

    def delete() {
        def id = params.long('id')
        if (!id) {
            render 'Please provide id'
            return
        }
        def departments = Department.findById(id)

        if (!departments) {
            render 'Invalid Id'
            return
        }
        departments.delete(flush: true)
        render 'department deleted'
    }
}
