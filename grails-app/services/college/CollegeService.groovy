package college

import grails.transaction.Transactional

@Transactional
class CollegeService {

    def getAllColleges() {
        def colleges = College.list()
        return colleges
    }

    def getCollegeById(def id) {
        def college = College.findById(id)

        if (!college) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }

        return college
    }

    def createCollege(College college) {
        if (!college.save()) {
            println "Unable to create college beacuse of ${college.errors.allErrors.defaultMessage.join(',')}"
            return [
                    status : 'error',
                    message: 'Unable to create college'
            ]
        }
        return [
                status : 'success',
                message: "College created with name: ${college.name}"
        ]
    }

    def updateCollege(College college) {
        if (!college) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        if (!college.save(flush: true)) {
            println "Unable to update college beacuse of ${college.errors.allErrors.defaultMessage.join(',')}"
            return [
                    status : 'error',
                    message: 'Unable to update college'
            ]
        }
        return [
                status : 'success',
                message: "College updated with name: ${college.name}"
        ]
    }

    def deleteCollege(def id) {
        def college = College.findById(id)

        if (!college) {
            return [
                    status : 'error',
                    message: 'Invalid Id'
            ]
        }
        college.delete(flush: true)

        return [
                status: 'success',
                message: 'college deleted'
        ]
    }
}
