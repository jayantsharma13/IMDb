import json
import os
from imdb import Cinemagoer
from tqdm import tqdm

# Initialize IMDb
ia = Cinemagoer()

# Configuration
TOP_N = 50 # Start small for testing, increase to 2500 later
DATA_DIR = "./frontend/public/data" # Save directly to React's public folder

# Ensure directory exists
os.makedirs(DATA_DIR, exist_ok=True)

def get_top_shows():
    print("Fetching Top 250 TV Shows...")
    # Cinemagoer has a method for the top 250
    top_250 = ia.get_top250_tv()
    return top_250[:TOP_N]

def save_show_details(show_id):
    try:
        # Fetch episodes info
        series = ia.get_movie(show_id)
        ia.update(series, 'episodes')
        
        heatmap_data = []
        
        # Iterate seasons
        if 'episodes' not in series:
            return None

        for season_nr, episodes in series['episodes'].items():
            season_episodes = []
            for ep_nr, episode in episodes.items():
                # Extract only what we need to keep file size small
                season_episodes.append({
                    "Episode": ep_nr,
                    "Title": episode.get('title', f"Episode {ep_nr}"),
                    "imdbRating": episode.get('rating') # Keeps original float/null
                })
            
            # Sort by episode number
            season_episodes.sort(key=lambda x: x['Episode'])
            
            heatmap_data.append({
                "Season": season_nr,
                "Episodes": season_episodes
            })
            
        # Sort by Season number
        heatmap_data.sort(key=lambda x: x['Season'])
        
        return heatmap_data
    except Exception as e:
        print(f"Error processing {show_id}: {e}")
        return None

# --- MAIN EXECUTION ---
shows_index = []
shows = get_top_shows()

print(f"Processing {len(shows)} shows...")

for show in tqdm(shows):
    imdb_id = f"tt{show.movieID}"
    title = show['title']
    
    # 1. Fetch detailed data
    details = save_show_details(show.movieID)
    
    if details:
        # 2. Save individual JSON file (e.g., data/tt0944947.json)
        with open(f"{DATA_DIR}/{imdb_id}.json", "w") as f:
            json.dump(details, f)
            
        # 3. Add to index
        shows_index.append({
            "id": imdb_id,
            "title": title
        })

# 4. Save the Master Index
with open(f"{DATA_DIR}/index.json", "w") as f:
    json.dump(shows_index, f)

print("Done! Data generated in", DATA_DIR)