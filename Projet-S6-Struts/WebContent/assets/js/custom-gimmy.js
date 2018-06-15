$('#cbCateg').on('change', function(){

	$.get('controller/ctrl-get-sous-categorie.php?categorie=' + $('#cbCateg').val(), function(json){

		var res = JSON.parse(json);

		if(res.error){

			if(res.message != null) alert(res.message);

		} else {

			$('.add-car-cbScateg-option').remove();

			res.data.forEach(function(item){

				$('<option value="' + item.ID + '" class="add-car-cbScateg-option">' + item.nom + '</option>').appendTo('#cbScateg');

			});

		}

	});

});