package college

import grails.converters.JSON

class CollegeController {

    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE"]

    def collegeService
    def validationService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def colleges = collegeService.getAllColleges()
        render colleges as JSON
    }

    def show() {
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        result = collegeService.getCollegeById(id)
        render result as JSON
    }

    def create(College college) {
        def result = collegeService.createCollege(college)
        render result as JSON
    }

    def update(College college) {
        def result = collegeService.updateCollege(college)
        render result as JSON
    }

    def delete() {
        def id = params.long('id')
        def result = validationService.validateId(id)
        if (result) {
            render result as JSON
            return
        }
        result = collegeService.deleteCollege(id)
        render result as JSON
    }
}
