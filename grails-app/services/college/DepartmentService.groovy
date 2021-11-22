package college

import grails.transaction.Transactional

@Transactional
class DepartmentService {

    def getAllDepartments() {
        def departments = Department.list()
        return departments
    }

    def getDepartmentById(def id) {
        def departments = Department.findById(id)

        if (!departments) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }

        return departments
    }

    def getStudents(def id) {
        def departments = Department.findById(id)

        if (!departments) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        return departments.students
    }

    def createDepartment(Department department) {
        if (!department.save()) {
            println "Unable to create department beacuse of ${department.errors.allErrors.defaultMessage.join(',')}"
            return [
                    status : 'error',
                    message: 'Unable to create department'
            ]
        }
        return [
                status : 'success',
                message: "Department created with name: ${department.name}"
        ]
    }

    def updateDepartment(Department department) {
        if (!department) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        if (!department.save(flush: true)) {
            println "Unable to update department beacuse of ${department.errors.allErrors.defaultMessage.join(',')}"
            return [
                    status : 'error',
                    message: 'Unable to update department'
            ]
        }
        return [
                status : 'success',
                message: "Department updated with name: ${department.name}"
        ]
    }

    def deleteDepartment(def id) {
        def departments = Department.findById(id)

        if (!departments) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        departments.delete(flush: true)

        return [
                status: 'success',
                message: 'department deleted'
        ]
    }
}
