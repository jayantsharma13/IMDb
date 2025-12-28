# IMDb Heatmap

A web application that visualizes TV show episode ratings as an interactive heatmap. Search for any TV series and see all episode ratings color-coded from red (low) to green (high).

## Features

- 🎬 Search any TV show by name
- 📊 Visual heatmap grid (Season × Episode)
- 🎨 Color-coded ratings (Red < 6.0, Orange 6.0-7.5, Light Green 7.5-8.9, Bright Green 9.0+)
- 🖼️ Show poster and plot summary
- ⚡ Parallel API calls for fast loading
- 🎭 Netflix-inspired dark theme

## Tech Stack

**Backend:**
- Spring Boot 3.3.4 (Java 17)
- OMDb API integration
- CompletableFuture for parallel season fetching

**Frontend:**
- React
- Tailwind CSS
- Lucide React icons

## Setup

### Prerequisites

- Java 17+
- Maven 3.6+
- Node.js 16+
- OMDb API key (get free at http://www.omdbapi.com/apikey.aspx)

### Backend Setup

1. Navigate to project root:
   ```bash
   cd c:\Users\91941\Desktop\IMDBHM
   ```

2. Add your OMDb API key in `src/main/java/com/example/imdbhm/service/OmdbService.java`:
   ```java
   private static final String API_KEY = "YOUR_API_KEY_HERE";
   ```

3. Build and run:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   Backend runs on http://localhost:8080

### Frontend Setup

1. Navigate to frontend:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start dev server:
   ```bash
   npm start
   ```

   Frontend runs on http://localhost:3000

## Usage

1. Enter a TV show name (e.g., "Friends", "Breaking Bad", "The Office")
2. Click Search or press Enter
3. View the color-coded heatmap with ratings
4. Hover over cells for episode details

## API Endpoints

- `GET /api/heatmap/{showName}` - Returns show metadata and all season/episode ratings

## Screenshots

*Add screenshots here after deployment*

## License

MIT

## Author

Built with ❤️ using Spring Boot and React
