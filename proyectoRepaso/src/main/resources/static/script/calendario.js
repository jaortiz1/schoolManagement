$(document).ready(function(){
	
/*<![CDATA[*/
	 var username = [[${listaReservas}]];
alert('asa');



/*]]>*/

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