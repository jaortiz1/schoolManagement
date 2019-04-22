$(document).ready(function(){
	
var fechasInicio =$('.fechasInicio');
var fechasFinal =$('.fechasFinal');
var textos = new Array();
var textos2 = new Array();
for (var i = 0; i < fechasInicio.length; i++) {
	textos.push(fechasInicio.text())
	
}
for (var i = 0; i < fechasFinal.length; i++) {
	textos2.push(fechasFinal.text())
}

$('#calendar').fullCalendar({
	

      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay,listWeek'
      },
      defaultDate: '2018-03-12',
      navLinks: true, // can click day/week names to navigate views
      editable: false,
      eventLimit: true, // allow "more" link when too many events
      
      events: [
    	  
            {
            	
              title: 'Reservado',
              start: '2018-10-07',
              end: '2018-10-10',
              color: 'red'
            
            }, 
            
            
    	  
      


        ]
      

      
    });


  });