// package umc.springumc.security.jwt.exception;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.extern.slf4j.Slf4j;
// import umc.springumc.apiPayload.global.ApiResponse;
// import umc.springumc.apiPayload.global.BaseErrorCode;
//
// @Slf4j
// public class JwtFilterExceptionHandler {
// 	public JwtFilterExceptionHandler(HttpServletResponse response, BaseErrorCode error) {
// 		response.setStatus(Integer.parseInt(error.getCode()));
// 		response.setContentType("application/json");
// 		response.setCharacterEncoding("UTF-8");
// 		try {
// 			String json = new ObjectMapper().writeValueAsString(
// 				ApiResponse.onFailure(error.getCode(), error.getMessage()));
// 			response.getWriter().write(json);
// 		} catch (Exception e) {
// 			log.warn(e.getMessage());
// 		}
// 	}
// }
