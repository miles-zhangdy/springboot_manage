package com.zdy.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("validatecode")
public class ValidateCodeController {
	private int width = 80;
	private int height = 30;

	@RequestMapping(value = "/getValidateCode")
	public void validateCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(
				ValidateCode.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute(Constant.VALIDATE_CODE, verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30,
				3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}

	@RequestMapping(value = "checkImage")
	public void checkImage(HttpServletRequest req, HttpServletResponse resp)
			throws java.io.IOException {

		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		Random random = new Random();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		Font font = new Font("Times New Roman", Font.PLAIN, 18);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setColor(Color.GRAY);
		StringBuffer randomCode = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String strRand = String.valueOf(random.nextInt(10));
			g.setColor(new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255)));
			g.drawString(strRand, 13 * i + 6, 16);
			randomCode.append(strRand);
		}

		HttpSession session = req.getSession();
		session.setAttribute("validateCode", randomCode.toString());
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		resp.setContentType("image/jpeg");
		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}
}
