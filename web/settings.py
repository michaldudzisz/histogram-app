## @file web/settings.py
#  @brief Django settings file

"""
Django settings file
"""

# Build paths inside the project like this: os.path.join(BASE_DIR, ...)

import os
BASE_DIR = os.path.dirname(os.path.dirname(__file__))

# SECURITY WARNING: keep the secret key used in production secret!
SECRET_KEY = 'kw$@=pa)zdjmjx^6z65-+x3c5j+^ydyj1!t!@_q+z2qw06&1*i'

# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True
TEMPLATE_DEBUG = True

ALLOWED_HOSTS = []

TEMPLATE_DIRS = (BASE_DIR, 
	"client/"
)

#TEMPLATES = [
#    {
#        'BACKEND': 'django.template.backends.django.DjangoTemplates',
#        'DIRS': [os.path.join(BASE_DIR,'client')],
#        'APP_DIRS': True,
#        'OPTIONS': {
#            # ... some options here ...
#        },
#    },
#]

# Application definition
INSTALLED_APPS = (
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
    'calcpy'
)

MIDDLEWARE_CLASSES = (
    'django.contrib.sessions.middleware.SessionMiddleware',
    'django.middleware.common.CommonMiddleware',
    'django.middleware.csrf.CsrfViewMiddleware',
    'django.contrib.auth.middleware.AuthenticationMiddleware',
    'django.contrib.messages.middleware.MessageMiddleware',
    'django.middleware.clickjacking.XFrameOptionsMiddleware',
)

ROOT_URLCONF = 'urls'

WSGI_APPLICATION = 'wsgi.application'

# Internationalization
# https://docs.djangoproject.com/en/1.6/topics/i18n/

LANGUAGE_CODE = 'en-us'

TIME_ZONE = 'UTC'

USE_I18N = True

USE_L10N = True

USE_TZ = True

#STATIC_URL = '/static/'
#STATIC_ROOT = os.path.join(BASE_DIR, 'static/')
#STATICFILES_DIRS = [os.path.join(BASE_DIR, 'static_files')]





#SITE_ROOT = os.path.dirname(os.path.realpath(__file__))

#STATICFILES_DIRS = (
#    os.path.join(SITE_ROOT, 'static'),
#    'static'
#)
#STATIC_ROOT = os.path.join(BASE_DIR, 'static')
#STATIC_URL = 'static/'


STATICFILES_DIRS = (
    os.path.join(BASE_DIR, "static"),
    'client',
)
#STATIC_URL = os.path.join(BASE_DIR, "client/")
STATIC_URL = '/static/'
#print(STATICFILES_DIRS)
# Static files (CSS, JavaScript, Images)
# https://docs.djangoproject.com/en/1.6/howto/static-files/

