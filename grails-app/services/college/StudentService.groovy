package college

import grails.transaction.Transactional

@Transactional
class StudentService {


    def getAllStudents() {
        def students = Student.list()
        return students
    }

    def getStudentById(def id) {
        def student = Student.findById(id)

        if (!student) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }

        return student
    }

    def createStudent(Student student) {
        if (!student.save()) {
            println "Unable to create student beacuse of ${student.errors.allErrors.defaultMessage.join(',')}"
            return [
                    status : 'error',
                    message: 'Unable to create student'
            ]
        }
        return [
                status : 'success',
                message: "Student created with name: ${student.name}"
        ]
    }

    def updateStudent(Student student) {
        if (!student) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        if (!student.save(flush: true)) {
            println "Unable to update student beacuse of ${student.errors.allErrors.defaultMessage.join(',')}"
            return [
                    status : 'error',
                    message: 'Unable to update student'
            ]
        }
        return [
                status : 'success',
                message: "Student updated with name: ${student.name}"
        ]
    }

    def deleteStudent(def id) {
        def student = Student.findById(id)

        if (!student) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        student.delete(flush: true)

        return [
                status: 'success',
                message: 'student deleted'
        ]
    }
}
