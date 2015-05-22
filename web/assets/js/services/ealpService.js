ealpApp.service("ealpService", function (utility, $http) {
    this.get = function (params, servlet) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: servlet,
            data: $.param(params),
            dataType: 'json'
        }).success(function (data) {
        }).error(function (data) {
        });
    };
    this.getWithAlert = function (params, servlet, alert) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: servlet,
            data: $.param(params),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "400" || data.responseCode == "403" || data.responseCode == "401") {
                utility.showAlert(alert, "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(alert, "alert", data.message, 5000);
        });
    }
    this.set = function (params, servlet, alert) {
        return $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST',
            url: servlet,
            data: $.param(params),
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == 200) {
                utility.showAlert(alert, "success", data.message, 5000);
            } else {
                utility.showAlert(alert, "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(alert, "alert", data.message, 5000);
        });
    };
    this.setActivities = function (alert, mydata) {
        return $http({
            headers: {'Content-Type': 'application/json'},
            method: 'POST',
            url: 'scholar1',
            dataType: 'JSON',
            data: JSON.stringify(mydata),
        }).success(function (data) {
            if (data.responseCode == "200") {

                utility.showAlert(alert, "success", data.message, 5000);
            } else {
                utility.showAlert(alert, "alert", data.message, 5000);
            }

        }).error(function (data) {
            utility.showAlert(alert, "alert", data.message, 5000);
        });
    };
    this.uploadDocument = function (fd, servlet, alert) {
        return $http({
            headers: {'Content-Type': undefined},
            method: 'POST',
            url: servlet,
            transformRequest: angular.identity,
            data: fd,
            dataType: 'json'
        }).success(function (data) {
            if (data.responseCode == "200") {
                utility.showAlert(alert, "success", data.message, 5000);
            } else {
                utility.showAlert(alert, "alert", data.message, 5000);
            }
        }).error(function (data) {
            utility.showAlert(alert, "alert", data.message, 5000);
        });
    }

})

