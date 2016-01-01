requirejs.config({
    baseUrl : './js',
    shim : {

        bootstrap : {
            deps : [ 'jquery'],
            exports: 'Bootstrap'
        },
        
        nicescroll : {
        	deps : ['jquery']
        },
        
        custom : {
        	deps : ['jquery', 'bootstrap', 'nicescroll']
        }
    },

    paths : {
        jquery : 'template/jquery.min',
        bootstrap : 'template/bootstrap.min',
        custom : 'template/custom',
        chartjs : 'template/chartjs/chart.min',
        progressbar : 'template/progressbar/bootstrap-progressbar.min',
        nicescroll : 'template/nicescroll/jquery.nicescroll.min',
        icheck : 'template/icheck/icheck.min',
        moris : 'template/moris/raphael-min'
    }
});


requirejs(["custom"], function(custom) {
	
});