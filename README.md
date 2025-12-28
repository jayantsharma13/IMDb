# IMDb Heatmap

<div align="center">

```
╦╔╦╗╔╦╗┌┐   ╦ ╦┌─┐┌─┐┌┬┐┌┬┐┌─┐┌─┐
║║║║ ║║├┴┐  ╠═╣├┤ ├─┤ │ │││├─┤├─┘
╩╩ ╩═╩╝└─┘  ╩ ╩└─┘┴ ┴ ┴ ┴ ┴┴ ┴┴  
```

### 🎬 Visualize TV Show Ratings Like Never Before 🎬

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18-61DAFB?style=for-the-badge&logo=react&logoColor=black)](https://reactjs.org/)
[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)

[✨ Features](#-features) • [🚀 Quick Start](#-quick-start) • [📸 Screenshots](#-screenshots) • [🛠️ Tech Stack](#️-tech-stack)

---

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

</div>

## 🎯 What is IMDb Heatmap?

Ever wondered which episodes of your favorite show are **pure gold** 🌟 and which ones are... meh? 

**IMDb Heatmap** transforms boring rating numbers into a **stunning visual grid** where:
- 🔴 **Red** = Skip it (< 6.0)
- 🟠 **Orange** = It's okay (6.0 - 7.5)
- 🟢 **Light Green** = Pretty good! (7.5 - 8.9)
- 💚 **Neon Green** = MASTERPIECE! (9.0+)

<div align="center">

```
     ┌─────────────────────────────────────┐
     │  Search → Fetch → Visualize → WOW  │
     └─────────────────────────────────────┘
```

</div>

## ✨ Features

<table>
<tr>
<td width="50%">

### 🎨 Visual Excellence
- **Color-coded heatmap** for instant quality insights
- **Netflix-inspired dark theme** 🌙
- **Responsive grid layout** adapting to any screen
- **Smooth animations** on hover

</td>
<td width="50%">

### ⚡ Performance
- **Parallel API calls** using CompletableFuture
- **Lightning-fast** season data fetching
- **Real-time search** with instant feedback
- **Optimized React rendering**

</td>
</tr>
<tr>
<td width="50%">

### 📺 Rich Data
- **Show poster** and plot summary
- **Every episode** with rating
- **All seasons** in one view
- **Tooltips** with episode titles

</td>
<td width="50%">

### 🛡️ Rock Solid
- **Spring Boot** backend REST API
- **CORS-enabled** for seamless integration
- **Error handling** with friendly messages
- **Clean architecture** following best practices

</td>
</tr>
</table>

## 🚀 Quick Start

### 📋 Prerequisites

```bash
☑️ Java 17+
☑️ Maven 3.6+
☑️ Node.js 16+
☑️ OMDb API Key (free at omdbapi.com)
```

### 🔧 Installation

<details>
<summary><b>🖥️ Backend Setup (Click to expand)</b></summary>

```bash
# 1️⃣ Clone the repository
git clone https://github.com/YOUR_USERNAME/imdb-heatmap.git
cd imdb-heatmap

# 2️⃣ Add your OMDb API key
# Edit: src/main/java/com/example/imdbhm/service/OmdbService.java
# Replace: private static final String API_KEY = "YOUR_KEY_HERE";

# 3️⃣ Build & Run
mvn clean install
mvn spring-boot:run

# ✅ Backend running on http://localhost:8080
```

</details>

<details>
<summary><b>🎨 Frontend Setup (Click to expand)</b></summary>

```bash
# 1️⃣ Navigate to frontend
cd frontend

# 2️⃣ Install dependencies
npm install

# 3️⃣ Start development server
npm start

# ✅ Frontend running on http://localhost:3000
```

</details>

### 🎮 Usage

```
┌─────────────────────────────────────────────────┐
│  1. Open http://localhost:3000                  │
│  2. Type a show name: "Breaking Bad"            │
│  3. Press Enter or click Search                 │
│  4. 🎉 Watch the magic happen!                  │
└─────────────────────────────────────────────────┘
```

## 📸 Screenshots

<div align="center">

### 🔍 Search Interface
*Clean, minimal, Netflix-inspired*

### 📊 Heatmap Grid
*Color-coded ratings at a glance*

### 🎭 Show Details
*Poster, plot, and all the data*

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

</div>

## 🛠️ Tech Stack

<div align="center">

| Layer | Technologies |
|-------|-------------|
| **Backend** | ![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white) ![Java](https://img.shields.io/badge/-Java%2017-007396?style=flat-square&logo=java&logoColor=white) ![Maven](https://img.shields.io/badge/-Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white) |
| **Frontend** | ![React](https://img.shields.io/badge/-React-61DAFB?style=flat-square&logo=react&logoColor=black) ![TailwindCSS](https://img.shields.io/badge/-Tailwind-06B6D4?style=flat-square&logo=tailwindcss&logoColor=white) ![JavaScript](https://img.shields.io/badge/-JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black) |
| **API** | ![OMDb](https://img.shields.io/badge/-OMDb%20API-FFD700?style=flat-square) ![REST](https://img.shields.io/badge/-REST-009688?style=flat-square) |
| **Tools** | ![Git](https://img.shields.io/badge/-Git-F05032?style=flat-square&logo=git&logoColor=white) ![VS Code](https://img.shields.io/badge/-VS%20Code-007ACC?style=flat-square&logo=visualstudiocode&logoColor=white) |

</div>

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────┐
│                    React Frontend                    │
│  (Search UI + Heatmap Grid + Show Details)          │
└────────────────────┬────────────────────────────────┘
                     │ HTTP GET
                     │
┌────────────────────▼────────────────────────────────┐
│              Spring Boot Backend                     │
│  ┌──────────────────────────────────────────────┐  │
│  │  HeatmapController                           │  │
│  │    ↓                                         │  │
│  │  OmdbService (CompletableFuture parallel)   │  │
│  └────────────────────┬─────────────────────────┘  │
└───────────────────────┼────────────────────────────┘
                        │ REST API
                        │
┌───────────────────────▼────────────────────────────┐
│                 OMDb API                            │
│  (Show metadata + Episode ratings)                 │
└────────────────────────────────────────────────────┘
```

## 🎯 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/heatmap/{showName}` | Fetch show metadata + all seasons/episodes |

**Response Example:**
```json
{
  "title": "Breaking Bad",
  "plot": "A high school chemistry teacher...",
  "poster": "https://...",
  "seasons": [
    {
      "season": 1,
      "episodes": [
        { "episode": 1, "title": "Pilot", "rating": 9.0 },
        ...
      ]
    },
    ...
  ]
}
```

## 🌐 Live Demo

🚀 **[View Live Application](https://your-app.vercel.app)**

## 🚢 Deployment

<details>
<summary><b>Deploy to Vercel + Render (Recommended)</b></summary>

### Frontend (Vercel)
```bash
cd frontend
vercel
```

### Backend (Render)
1. Push to GitHub
2. Connect to Render.com
3. Add `OMDB_API_KEY` environment variable
4. Deploy!

</details>

<details>
<summary><b>Deploy with Docker</b></summary>

```bash
docker-compose up --build
```

</details>

## 🤝 Contributing

Contributions are **welcome**! Feel free to:

1. 🍴 Fork the repository
2. 🌿 Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. 💾 Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. 📤 Push to the branch (`git push origin feature/AmazingFeature`)
5. 🎉 Open a Pull Request

## 📜 License

Distributed under the **MIT License**. See `LICENSE` for more information.

## 🌟 Star History

[![Star History Chart](https://api.star-history.com/svg?repos=YOUR_USERNAME/imdb-heatmap&type=Date)](https://star-history.com/#YOUR_USERNAME/imdb-heatmap&Date)

---

<div align="center">

### 💡 Built with passion by developers, for TV enthusiasts 📺

**If you found this useful, give it a ⭐!**

[Report Bug](https://github.com/YOUR_USERNAME/imdb-heatmap/issues) • [Request Feature](https://github.com/YOUR_USERNAME/imdb-heatmap/issues)

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%">

**Made with ❤️ using Spring Boot & React**

</div>
