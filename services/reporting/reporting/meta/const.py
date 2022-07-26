"""Application constants and error messages."""
from typing import Final


E_ROUTE_NOT_FOUND: Final[str] = 'The requested route does not exist.'
E_METHOD_NOT_ALLOWED: Final[str] = 'The requested route and method is not supported.'
E_INTERNAL_SERVER_ERROR: Final[str] = 'An internal server exception has occurred.'
E_UNAUTHORIZED: Final[str] = 'You are not authorized to access this resource.'
E_REPORT_GET: Final[str] = 'An exception occurred while retrieving the report.'
E_REPORT_GET_ALL: Final[str] = 'An exception occurred while retrieving all reports.'
E_REPORT_CREATE: Final[str] = 'An exception occurred while creating the report.'
E_REPORT_CREATE_INVALID_INPUT: Final[
    str
] = 'An exception occurred while creating the report: the provided payload was invalid.'
