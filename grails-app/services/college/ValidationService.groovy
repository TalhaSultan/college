package college

import grails.transaction.Transactional

@Transactional
class ValidationService {


    def validateId(def id, String entity = null) {
        def result = null
        if (!id) {
            result = [
                    status : 'error',
                    message: "Please provide ${entity} id"
            ]
        }

        return result
    }

    def validateCourse(def courseId){

        def course = Course.findById(courseId)
        def result = null
        if (!course) {
            result = [
                    status : 'error',
                    message: 'Please provide valid course id'
            ]
        }

        return result
    }

    def validateStudent(def studentId){

        def student = Student.findById(studentId)
        def result = null
        if (!student) {
            result = [
                    status : 'error',
                    message: 'Please provide valid student id'
            ]
        }

        return result
    }
}
