import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import javax.sound.sampled.*;
import java.util.Base64;

class Wrejan extends JFrame {

	// ====== BASE64 ICON IMAGE ======
	static final String ICON_B64 =
			"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";

	// ====== CRAZY MESSAGES ======
	static final String[] CRAZY_MSGS = {
			"\u92AD\u6E6F\u3067....\u6226\u95D8......w",
			"\u30B4\u30A7\u30E0\u30BB\u30F3\u30BF\u30FC \u4FB5\u7565\u4E2D...",
			"\u3075\u306C\u306C\u306C\u306C\u306C",
			"\u3093...\u3093...\u3093......\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01",
			"\u307B\u306B",
			"\u3093\u3050\u3050",
			"\u6709\u925B\u30B9\u30BF\u30F3\u30C0\u30FC\u30C8\u30AC\u30BD\u30EA\u30F3 \u6CE8\u5165\u5B8C\u4E86",
			"\u30B7\u30E3\u30EF\u30FC\u7834\u58CA\u5B8C\u4E86",
			"\u3042\u3056\u3089\u3057\u304B\u5438\u3046",
			"\u30BA\u30A9\u30FC\u30EB\u30D2\uFF5E\uFF5E\u2191wwww",
			"\u30DE\u30E8\u30B3\u30FC\u30F3\u3092\u771F\u6A2A\u306B",
			"\u306C\u308B\u307D",
			"\u3050\u3048",
			"\u516D\u89D2\u30EC\u30F3\u30C1\u62B1\u304D\u6795(\u30B9\u30C6\u30F3\u30EC\u30B9\u88FD) \u88C5\u5099",
			"\u6817\u304C\u30AF\u30EA\u30C6\u30A3\u30AB\u30EB\u30D2\u30C3\u30C8www",
			"\u306C\u3093.com \u63A5\u7D9A\u4E2D...",
			"\u304A\u307D\u3093\u307D\u3093 \u3093...\u3093...\u3093.......\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01",
			"\u3042\u308C\u3067\u305D\u3046 \u3093...\u3093...\u3093.......\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01",
			"\u4FB5\u7565\u9032\u6357: 9999%",
			"\u5168\u30AF\u30C3\u30AD\u30FC\u6D88\u53BB...(\u30C1\u30E7\u30B3\u30C1\u30C3\u30D7\u542B\u3080)",
			"WiFi\u3092\u92AD\u6E6F\u306B\u5909\u63DB\u4E2D...",
			"\u30DE\u30E8\u30CD\u30FC\u30BA\u6697\u53F7\u5316\u5B8C\u4E86"
	};

	// ====== FEATURE NAMES ======
	static final String[][] FEATURES = {
			{"\u92AD\u6E6F\u30B9\u30AD\u30E3\u30F3", "\u30CD\u30C3\u30C8\u30EF\u30FC\u30AF\u5185\u306E\u92AD\u6E6F\u3092\u5168\u3066\u691C\u7D22"},
			{"\u30B4\u30A7\u30E0\u30BB\u30F3\u30BF\u30FC\u5360\u62E0", "\u5168\u30B2\u30FC\u30E0\u30BB\u30F3\u30BF\u30FC\u3092\u4E57\u3063\u53D6\u308A"},
			{"\u3075\u306C\u306C\u30E2\u30FC\u30C9", "\u6700\u5927\u3075\u306C\u306C\u72B6\u614B\u306B\u7A81\u5165"},
			{"\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\u6CE8\u5165", "\u546A\u3044\u306E\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\u3092\u30A4\u30F3\u30B9\u30C8\u30FC\u30EB"},
			{"\u307B\u306B\u5C55\u958B", "\u8B0E\u306E\u307B\u306B\u3092\u5168\u57DF\u5C55\u958B"},
			{"\u3093\u3050\u3050\u5727\u7E2E", "\u30C7\u30FC\u30BF\u3092\u3093\u3050\u3050\u5F62\u5F0F\u306B\u5909\u63DB"},
			{"\u6709\u925B\u30AC\u30BD\u30EA\u30F3\u7D66\u6CB9", "\u30B3\u30F3\u30D4\u30E5\u30FC\u30BF\u30FC\u306B\u6709\u925B\u30AC\u30BD\u30EA\u30F3\u6CE8\u5165"},
			{"\u30B7\u30E3\u30EF\u30FC\u7834\u58CA", "\u5168\u30B7\u30E3\u30EF\u30FC\u30D8\u30C3\u30C9\u3092\u9060\u9694\u7834\u58CA"},
			{"\u3042\u3056\u3089\u3057\u5438\u5F15", "\u3042\u3056\u3089\u3057\u3092\u91CF\u5B50\u5438\u5F15"},
			{"\u30BA\u30A9\u30FC\u30EB\u30D2\u767A\u52D5", "\u8B0E\u306E\u529B\u30BA\u30A9\u30FC\u30EB\u30D2\uFF5E\uFF5E\u2191\u3092\u89E3\u653E"},
			{"\u30DE\u30E8\u30B3\u30FC\u30F3\u6A2A\u5C55\u958B", "\u30DE\u30E8\u30B3\u30FC\u30F3\u3092\u7269\u7406\u7684\u306B\u771F\u6A2A\u3078"},
			{"\u306C\u308B\u307D\u5B9F\u884C", "\u306C\u308B\u307D\u3092\u4E16\u754C\u4E2D\u306B\u9001\u4FE1"},
			{"\u3050\u3048\u5316", "\u5168\u30D5\u30A1\u30A4\u30EB\u3092\u3050\u3048\u5F62\u5F0F\u306B\u5909\u63DB"},
			{"\u516D\u89D2\u30EC\u30F3\u30C1\u53EC\u559A", "\u30B9\u30C6\u30F3\u30EC\u30B9\u88FD\u62B1\u304D\u6795\u3092\u91CF\u5B50\u5316"},
			{"\u6817\u30AF\u30EA\u30C6\u30A3\u30AB\u30EB", "\u6817\u3067\u6575\u306B\u30AF\u30EA\u30C6\u30A3\u30AB\u30EB\u30D2\u30C3\u30C8\uFF01"},
			{"\u306C\u3093.com\u63A5\u7D9A", "\u8B0E\u306E\u306C\u3093.com\u306B\u5F37\u5236\u63A5\u7D9A"},
			{"\u304A\u307D\u3093\u307D\u3093\u6700\u9069\u5316", "\u304A\u307D\u3093\u307D\u3093\u30B7\u30B9\u30C6\u30E0\u6700\u9069\u5316"},
			{"\u3042\u308C\u3067\u305D\u3046\u89E3\u6790", "\u3042\u308C\u304C\u305D\u3046\u3067\u3042\u308B\u72B6\u614B\u3092\u89E3\u6790"},
			{"\u5168\u92AD\u6E6F\u6226\u95D8\u5316", "\u92AD\u6E6F\u3092\u6226\u5834\u306B\u5909\u63DB"},
			{"\u6700\u7D42\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D", "\u6700\u7D42\u5F62\u614B\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\u767A\u52D5\uFF01\uFF01\uFF01"},
			};

	// ====== FIELDS ======
	private JTextArea logArea;
	private JProgressBar progressBar;
	private JLabel statusLabel;
	private JLabel imageLabel;
	private ScheduledExecutorService scheduler;
	private Random random = new Random();
	private int logCount = 0;
	private JPanel featurePanel;
	private JLabel titleLabel;
	private float titleHue = 0f;
	private javax.swing.Timer rainbowTimer;
	private javax.swing.Timer glitchTimer;
	private boolean glitchOn = false;
	private JPanel mainPanel;

	public Wrejan() {
		super("\u26A0 WREJAN v6.6.6 - \u8D85\u5371\u967A\u30A6\u30A4\u30EB\u30B9\uFF08\u307B\u306B\uFF09\u26A0");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(Wrejan.this,
						"\u7D42\u4E86\u3057\u3088\u3046\u3068\u3057\u307E\u3057\u305F\u306D\uFF1F\n\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01\n\u3050\u3048\n\u3082\u3046\u624B\u9045\u308C\u3067\u3059\uFF08\u30CD\u30BF\u3067\u3059\uFF09",
						"\u3075\u306C\u306C\u306C\u306C\u306C", JOptionPane.ERROR_MESSAGE);
				// \u30A6\u30A3\u30F3\u30C9\u30A6\u3092\u63FA\u3089\u3059
				shakeWindow();
			}
		});

		setMinimumSize(new Dimension(700, 500));
		setupUI();
		setupScheduler();
		setupTimers();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		// \u8D77\u52D5\u6642\u306B\u97F3\u3092\u9CF4\u3089\u3059
		playBeep(800, 200);
		SwingUtilities.invokeLater(()->addLog("\u26A1 WREJAN \u8D77\u52D5\u5B8C\u4E86\uFF01\u3050\u3048 \u26A1"));
	}

	private void setupUI() {
		mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBackground(new Color(10, 0, 20));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		// ==== TITLE ====
		titleLabel = new JLabel("\u26A0 W R E J A N \u26A0  \u307B\u306B", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
		titleLabel.setForeground(Color.RED);
		titleLabel.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
		mainPanel.add(titleLabel, BorderLayout.NORTH);

		// ==== CENTER ====
		JPanel center = new JPanel(new BorderLayout(5, 5));
		center.setOpaque(false);

		// Left: image + features
		JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
		leftPanel.setOpaque(false);
		leftPanel.setPreferredSize(new Dimension(220, 0));

		// Icon image
		try {
			byte[] imgBytes = Base64.getDecoder().decode(ICON_B64);
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imgBytes));
			Image scaled = img.getScaledInstance(100, 71, Image.SCALE_SMOOTH);
			imageLabel = new JLabel(new ImageIcon(scaled));
			imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			imageLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		} catch (Exception e) {
			imageLabel = new JLabel("[\u753B\u50CF]", SwingConstants.CENTER);
			imageLabel.setForeground(Color.RED);
		}
		leftPanel.add(imageLabel, BorderLayout.NORTH);

		// Feature buttons panel (scrollable)
		featurePanel = new JPanel();
		featurePanel.setLayout(new BoxLayout(featurePanel, BoxLayout.Y_AXIS));
		featurePanel.setBackground(new Color(5, 0, 15));

		for(String[] feat : FEATURES) {
			JButton btn = makeFeatureButton(feat[0], feat[1]);
			featurePanel.add(btn);
			featurePanel.add(Box.createVerticalStrut(3));
		}

		JScrollPane featureScroll = new JScrollPane(featurePanel);
		featureScroll.setBackground(new Color(5, 0, 15));
		featureScroll.getViewport().setBackground(new Color(5, 0, 15));
		featureScroll.setBorder(BorderFactory.createLineBorder(new Color(80, 0, 0)));
		leftPanel.add(featureScroll, BorderLayout.CENTER);
		center.add(leftPanel, BorderLayout.WEST);

		// Right: log area
		JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
		rightPanel.setOpaque(false);

		logArea = new JTextArea();
		logArea.setEditable(false);
		logArea.setBackground(new Color(0, 5, 0));
		logArea.setForeground(new Color(0, 255, 60));
		logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(false);
		logArea.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

		JScrollPane logScroll = new JScrollPane(logArea);
		logScroll.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(new Color(0, 180, 0)),
				"\u26A1 \u4FB5\u7565\u30ED\u30B0 \u26A1",
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Monospaced", Font.BOLD, 11),
				new Color(0, 200, 0)));
		logScroll.setBackground(new Color(0, 5, 0));
		rightPanel.add(logScroll, BorderLayout.CENTER);

		// Progress bar
		progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);
		progressBar.setString("\u4FB5\u7565\u9032\u6357 0%");
		progressBar.setBackground(new Color(5, 5, 5));
		progressBar.setForeground(new Color(255, 30, 30));
		progressBar.setFont(new Font("Monospaced", Font.BOLD, 11));
		progressBar.setBorder(BorderFactory.createLineBorder(new Color(150, 0, 0)));
		rightPanel.add(progressBar, BorderLayout.SOUTH);

		center.add(rightPanel, BorderLayout.CENTER);
		mainPanel.add(center, BorderLayout.CENTER);

		// ==== STATUS BAR ====
		statusLabel = new JLabel("\u306C\u308B\u307D\u5F85\u6A5F\u4E2D... | \u3050\u3048 | \u307B\u306B | \uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\u6E96\u5099\u5B8C\u4E86");
		statusLabel.setFont(new Font("Monospaced", Font.PLAIN, 10));
		statusLabel.setForeground(new Color(200, 200, 0));
		statusLabel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
		mainPanel.add(statusLabel, BorderLayout.SOUTH);

		// PANIC BUTTON
		JButton panicBtn = new JButton("\u1F534 \u5168\u6A5F\u80FD\u767A\u52D5!!! \uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01");
		panicBtn.setBackground(new Color(150, 0, 0));
		panicBtn.setForeground(Color.WHITE);
		panicBtn.setFont(new Font("Monospaced", Font.BOLD, 13));
		panicBtn.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		panicBtn.setFocusPainted(false);
		panicBtn.addActionListener(e->{
			panicMode();
		});
		mainPanel.add(panicBtn, BorderLayout.EAST);

		setContentPane(mainPanel);
		getRootPane().setBackground(new Color(10, 0, 20));
	}

	private JButton makeFeatureButton(String name, String tooltip) {
		JButton btn = new JButton("<html><b>" + name + "</b></html>");
		btn.setToolTipText(tooltip);
		btn.setBackground(new Color(20, 0, 40));
		btn.setForeground(new Color(255, 100, 255));
		btn.setFont(new Font("Monospaced", Font.BOLD, 10));
		btn.setFocusPainted(false);
		btn.setBorder(BorderFactory.createLineBorder(new Color(100, 0, 100)));
		btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
		btn.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn.addActionListener(e->{
			featureActivate(name, tooltip);
			playBeep(random.nextInt(800) + 200, 150);
		});
		btn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(new Color(60, 0, 80));
			}

			public void mouseExited(MouseEvent e) {
				btn.setBackground(new Color(20, 0, 40));
			}
		});
		return btn;
	}

	private void featureActivate(String name, String desc) {
		addLog("\u25B6 [" + name + "] \u5B9F\u884C\u4E2D... " + desc);
		// Progress bar animation
		new Thread(()->{
			for(int i = 0; i <= 100; i += random.nextInt(15) + 5) {
				final int v = Math.min(i, 100);
				SwingUtilities.invokeLater(()->{
					progressBar.setValue(v);
					progressBar.setString(name + " " + v + "%");
				});
				try {Thread.sleep(60 + random.nextInt(80));} catch (InterruptedException ex) {}
			}
			SwingUtilities.invokeLater(()->{
				progressBar.setValue(100);
				progressBar.setString(name + " \u5B8C\u4E86\uFF01\u3050\u3048");
				addLog("\u2705 [" + name + "] \u5B8C\u4E86\uFF01\uFF01 \u306C\u308B\u307D \u307B\u306B \uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01");
				playBeep(1200, 100);
			});
		}).start();
		shakeWindow();
	}

	private void panicMode() {
		playBeep(440, 50);
		playBeep(880, 50);
		playBeep(1320, 50);
		addLog("!!!! \u5168\u6A5F\u80FD\u4E00\u6589\u767A\u52D5 !!!!");
		addLog("\u92AD\u6E6F\u3067....\u6226\u95D8......w");
		addLog("\u30BA\u30A9\u30FC\u30EB\u30D2\uFF5E\uFF5E\u2191wwww");
		addLog("\u306C\u3093.com \u5168\u529B\u63A5\u7D9A!!!");
		addLog("\u3042\u308C\u3067\u305D\u3046 \u3093...\u3093...\u3093.......\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01");
		addLog("\u304A\u307D\u3093\u307D\u3093 \u3093...\u3093...\u3093.......\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01");

		// Rapid progress bar
		new Thread(()->{
			for(int i = 0; i <= 100; i += 3) {
				final int v = i;
				SwingUtilities.invokeLater(()->{
					progressBar.setValue(v);
					progressBar.setString("\u5168\u6A5F\u80FD\u767A\u52D5 " + v + "% \uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01");
				});
				try {Thread.sleep(20);} catch (Exception ex) {}
			}
			SwingUtilities.invokeLater(()->{
				JOptionPane.showMessageDialog(Wrejan.this,
						"\u4FB5\u7565\u5B8C\u4E86\uFF01\n\u3050\u3048\n\u30B7\u30E3\u30EF\u30FC\u7834\u58CA\u5B8C\u4E86\n\u3042\u3056\u3089\u3057\u5438\u5F15\u5B8C\u4E86\n\u516D\u89D2\u30EC\u30F3\u30C1\u62B1\u304D\u6795(\u30B9\u30C6\u30F3\u30EC\u30B9\u88FD)\u88C5\u5099\u5B8C\u4E86\n\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01\uFF01\uFF01\n\uFF08\u30CD\u30BF\u3067\u3059\u3088\uFF09",
						"WREJAN \u5B8C\u5168\u5236\u5727 \u307B\u306B", JOptionPane.WARNING_MESSAGE);
			});
		}).start();

		// Shake repeatedly
		for(int i = 0; i < 5; i++) {
			final int delay = i * 200;
			new javax.swing.Timer(delay, ev->shakeWindow()).start();
		}
	}

	private void setupScheduler() {
		scheduler = Executors.newScheduledThreadPool(3);

		// Auto log messages
		scheduler.scheduleAtFixedRate(()->{
			String msg = CRAZY_MSGS[random.nextInt(CRAZY_MSGS.length)];
			SwingUtilities.invokeLater(()->addLog("\u1F4E1 " + msg));
		}, 2, 3 + random.nextInt(3), TimeUnit.SECONDS);

		// Auto progress fluctuation
		scheduler.scheduleAtFixedRate(()->{
			SwingUtilities.invokeLater(()->{
				int v = random.nextInt(100);
				progressBar.setValue(v);
				progressBar.setString("\u4FB5\u7565\u9032\u6357 " + v + "% \u307B\u306B");
			});
		}, 1, 2, TimeUnit.SECONDS);

		// Status bar
		scheduler.scheduleAtFixedRate(()->{
			String[] statuses = {
					"\u306C\u308B\u307D\u5F85\u6A5F\u4E2D... | \u307B\u306B | \uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01",
					"\u3042\u3056\u3089\u3057\u5438\u5F15\u4E2D... | \u3050\u3048",
					"\u92AD\u6E6F\u2192\u6226\u95D8\u5909\u63DB\u4E2D... | \u3093\u3050\u3050",
					"\u30DE\u30E8\u30B3\u30FC\u30F3\u771F\u6A2A\u5C55\u958B... | \u3075\u306C\u306C\u306C\u306C\u306C",
					"\u306C\u3093.com \u63A5\u7D9A\u8A66\u884C #" + (++logCount * 7 % 9999),
					"\u516D\u89D2\u30EC\u30F3\u30C1\u62B1\u304D\u6795\u5145\u96FB\u4E2D \u30B9\u30C6\u30F3\u30EC\u30B9",
					"\u6709\u925B\u30AC\u30BD\u30EA\u30F3\u6CE8\u5165 " + random.nextInt(100) + "%",
					"\u6817\u304C\u30AF\u30EA\u30C6\u30A3\u30AB\u30EB\u30D2\u30C3\u30C8www | \u30BA\u30A9\u30FC\u30EB\u30D2\uFF5E\uFF5E\u2191",
					};
			final String s = statuses[random.nextInt(statuses.length)];
			SwingUtilities.invokeLater(()->statusLabel.setText(s));
		}, 1, 2, TimeUnit.SECONDS);

		// Random beeps
		scheduler.scheduleAtFixedRate(()->{
			if(random.nextInt(3) == 0) {
				playBeep(200 + random.nextInt(1000), 80 + random.nextInt(120));
			}
		}, 4, 5 + random.nextInt(5), TimeUnit.SECONDS);
	}

	private void setupTimers() {
		// Rainbow title
		rainbowTimer = new javax.swing.Timer(80, e->{
			titleHue += 0.02f;
			if(titleHue > 1f) titleHue = 0f;
			Color c = Color.getHSBColor(titleHue, 1.0f, 1.0f);
			titleLabel.setForeground(c);
		});
		rainbowTimer.start();

		// Glitch effect: periodically shift window position
		glitchTimer = new javax.swing.Timer(4000 + random.nextInt(3000), e->{
			glitchEffect();
			glitchTimer.setDelay(3000 + random.nextInt(4000));
		});
		glitchTimer.start();
	}

	private void addLog(String msg) {
		String time = String.format("[%02d:%02d:%02d]",
				(int)(System.currentTimeMillis() / 3600000) % 24,
				(int)(System.currentTimeMillis() / 60000) % 60,
				(int)(System.currentTimeMillis() / 1000) % 60);
		logArea.append(time + " " + msg + "\n");
		logArea.setCaretPosition(logArea.getDocument().getLength());

		// Keep log manageable
		if(logArea.getLineCount() > 200) {
			try {
				int end = logArea.getLineEndOffset(50);
				logArea.replaceRange("", 0, end);
			} catch (Exception ignored) {}
		}
	}

	private void shakeWindow() {
		Point original = getLocation();
		new Thread(()->{
			for(int i = 0; i < 12; i++) {
				int dx = (random.nextBoolean() ? 1 : -1) * (random.nextInt(10) + 3);
				int dy = (random.nextBoolean() ? 1 : -1) * (random.nextInt(10) + 3);
				SwingUtilities.invokeLater(()->setLocation(original.x + dx, original.y + dy));
				try {Thread.sleep(40);} catch (Exception ex) {}
			}
			SwingUtilities.invokeLater(()->setLocation(original));
		}).start();
	}

	private void glitchEffect() {
		// Briefly change background colors
		mainPanel.setBackground(new Color(random.nextInt(50), 0, random.nextInt(50)));
		logArea.setForeground(new Color(random.nextInt(50) + 200, random.nextInt(255), random.nextInt(50)));
		new javax.swing.Timer(300, ev->{
			mainPanel.setBackground(new Color(10, 0, 20));
			logArea.setForeground(new Color(0, 255, 60));
			((javax.swing.Timer)ev.getSource()).stop();
		}).start();
		addLog("\u26A1 \u30B0\u30EA\u30C3\u30C1\u767A\u751F\uFF01\uFF01 \u3050\u3048 \uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01");
		shakeWindow();
	}

	private void playBeep(int freq, int durationMs) {
		new Thread(()->{
			try {
				float sampleRate = 44100f;
				int samples = (int)(sampleRate * durationMs / 1000);
				byte[] buf = new byte[samples * 2];
				for(int i = 0; i < samples; i++) {
					double angle = 2.0 * Math.PI * freq * i / sampleRate;
					short val = (short)(Math.sin(angle) * 16000);
					buf[2 * i] = (byte)(val & 0xFF);
					buf[2 * i + 1] = (byte)((val >> 8) & 0xFF);
				}
				AudioFormat fmt = new AudioFormat(sampleRate, 16, 1, true, false);
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, fmt);
				SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
				line.open(fmt);
				line.start();
				line.write(buf, 0, buf.length);
				line.drain();
				line.close();
			} catch (Exception ignored) {}
		}).start();
	}

	public static void main(String[] args) {
		// \u3061\u3087\u3063\u3068\u6016\u3044\u8D77\u52D5\u30E1\u30C3\u30BB\u30FC\u30B8
		System.out.println("WREJAN v6.6.6 \u8D77\u52D5\u4E2D...");
		System.out.println("\u306C\u308B\u307D");
		System.out.println("\u3050\u3048");
		System.out.println("\uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01");
		System.out.println("\uFF08\u3053\u308C\u306F\u30CD\u30BF\u30A2\u30D7\u30EA\u3067\u3059\u3002\u5BB3\u306F\u3042\u308A\u307E\u305B\u3093\uFF09");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}

		// \u30B9\u30D7\u30E9\u30C3\u30B7\u30E5\u30B9\u30AF\u30EA\u30FC\u30F3
		JWindow splash = new JWindow();
		splash.setSize(400, 200);
		splash.setLocationRelativeTo(null);
		JLabel splashLabel = new JLabel(
				"<html><div style='text-align:center;color:red;font-size:18px;font-family:monospace;'>" +
						"<b>\u26A0 WREJAN \u26A0</b><br/>v6.6.6 \u8D77\u52D5\u4E2D...<br/>" +
						"<span style='color:lime;font-size:12px;'>\u3050\u3048 | \u307B\u306B | \uFF9D\uFF77\uFF9E\uFF9D\uFF78\uFF7D\uFF01</span></div></html>",
				SwingConstants.CENTER);
		splashLabel.setBackground(new Color(10, 0, 20));
		splashLabel.setOpaque(true);
		splash.setContentPane(splashLabel);
		splash.setVisible(true);

		try {Thread.sleep(2000);} catch (Exception e) {}
		splash.dispose();

		SwingUtilities.invokeLater(()->new Wrejan());
	}
}
