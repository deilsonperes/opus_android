package com.d_peres.xiph.opus;

@SuppressWarnings("WeakerAccess")
public class OpusError extends RuntimeException {
	
	// Error Codes
	private static final int OPUS_OK               =  0;
	private static final int OPUS_BAD_ARG          = -1;
	private static final int OPUS_BUFFER_TOO_SMALL = -2;
	private static final int OPUS_INTERNAL_ERROR   = -3;
	private static final int OPUS_INVALID_PACKET   = -4;
	private static final int OPUS_UNIMPLEMENTED    = -5;
	private static final int OPUS_INVALID_STATE    = -6;
	private static final int OPUS_ALLOC_FAIL       = -7;
	
	//todo add documentation
	/* No Error */
	/* One or more invalid/out of range arguments */
	/* Not enough bytes allocated in the buffer */
	/* An internal error was detected  */
	/* The compressed data passed is corrupted */
	/* Invalid/unsupported request number */
	/* An encoder or decoder structure is invalid or already freed */
	/* Memory allocation has failed */
	
	static void throwOnError(int error_code){
		if(error_code < OPUS_OK) {
			throw new RuntimeException("Opus error (" + error_code + "): " + getErrorStr(error_code));
		}
	}
	
	/**
	 * Converts an error code to a string
	 * @param error The error code
	 * @return An error code converted to string
	 */
	private static String getErrorStr(int error) {
		switch (error) {
			case (OPUS_BAD_ARG):
				return "OPUS_BAD_ARG";
			case (OPUS_BUFFER_TOO_SMALL):
				return "OPUS_BUFFER_TOO_SMALL";
			case (OPUS_INTERNAL_ERROR):
				return "OPUS_INTERNAL_ERROR";
			case (OPUS_INVALID_PACKET):
				return "OPUS_INVALID_PACKET";
			case (OPUS_UNIMPLEMENTED):
				return "OPUS_UNIMPLEMENTED";
			case (OPUS_INVALID_STATE):
				return "OPUS_INVALID_STATE";
			case (OPUS_ALLOC_FAIL):
				return "OPUS_ALLOC_FAIL";
			default:
				return "OPUS_OK";
		}
	}
	
}
