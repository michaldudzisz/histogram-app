## @file web/views.py
#  @brief main server interface to client

"""
main interface to server
"""

import django.http
import json
import traceback

#all modules should be imported here

#from django.template import Context, loader
#from django.shortcuts import render
from django.shortcuts import render_to_response
from django.template import RequestContext

## for test working server
def app(request):
    """for test working server"""
    #template = loader.get_template("main-app.html")
    return render_to_response("main-app.html", {}, context_instance=RequestContext(request))

def ajax(request, module, function):
    """dispatch ajax requests"""
    try:
        fun = getattr(getattr(globals()[str(module)], 'views'), str(function))
        data = json.dumps( fun(request.GET) )
        return django.http.HttpResponse(data, content_type='application/json')
    except Exception as e:
        return django.http.HttpResponseNotFound("myapp ajax error: " + str(traceback.format_exc()) )
    except:
        return django.http.HttpResponseNotFound("myapp ajax system error " )
