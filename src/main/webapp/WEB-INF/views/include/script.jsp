<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/progressbar/bootstrap-progressbar.min.js"></script>
<script type="text/javascript" src="/resources/js/icheck/icheck.min.js"></script>
<script type="text/javascript" src="/resources/js/custom.js"></script>
<script>
    NProgress.done();
    $(document).ready(function () {
       $.ajax({
           method: 'GET',
           url: '/user',
           dataType: 'json',
           success: function (data) {
               $('.span-user').html(data.displayName);
           }
       });
    });
</script>
