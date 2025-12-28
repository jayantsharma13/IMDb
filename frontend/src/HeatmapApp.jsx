import React, { useState } from "react";
import { Search } from "lucide-react";

const colorForRating = (r) => {
	if (r == null) return "#374151"; // gray-700 for no rating
	if (r < 6.0) return "#ef4444";   // red
	if (r < 7.5) return "#f97316";   // orange
	if (r < 9.0) return "#84cc16";   // light green
	return "#22c55e";                // neon green
};

export default function HeatmapApp() {
	const [query, setQuery] = useState("");
	const [data, setData] = useState([]);
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState("");
	const [showMeta, setShowMeta] = useState({ title: "", plot: "", poster: "" });

	const fetchData = async () => {
		if (!query.trim()) return;
		setLoading(true);
		setError("");
		setData([]);
		try {
			const backendUrl = process.env.REACT_APP_BACKEND_URL || "http://localhost:8080";
			const url = `${backendUrl}/api/heatmap/${encodeURIComponent(query.trim())}`;
			console.log("Fetching:", url);
			const res = await fetch(url);
			if (!res.ok) throw new Error("Show not found or backend unreachable");
			const json = await res.json();
			setShowMeta({
				title: json.title || query,
				plot: json.plot || "",
				poster: json.poster || "",
			});
			setData(json.seasons || []);
		} catch (e) {
			console.error("Fetch error:", e);
			setError(e.message || "Failed to load data");
			setShowMeta({ title: "", plot: "", poster: "" });
		} finally {
			setLoading(false);
		}
	};

	return (
		<div className="min-h-screen w-full bg-gradient-to-b from-black via-[#0b0b0f] to-black text-gray-100 px-3 py-6">
			<div className="max-w-6xl mx-auto space-y-4">
				<h1 className="text-3xl font-bold text-white drop-shadow">IMDb Heatmap</h1>

				<div className="flex items-center gap-2 bg-[#141414] border border-red-700/40 rounded-lg px-4 py-3 shadow-[0_10px_25px_rgba(0,0,0,0.45)]">
					<Search className="text-gray-400" />
					<input
						className="w-full bg-transparent outline-none text-gray-100 placeholder-gray-500"
						placeholder="Search TV Show (e.g., Friends)"
						value={query}
						onChange={(e) => setQuery(e.target.value)}
						onKeyDown={(e) => e.key === "Enter" && fetchData()}
					/>
					<button
						className="px-4 py-2 bg-[#e50914] hover:bg-[#f6121d] active:bg-[#b20710] transition-colors rounded-md text-sm font-semibold shadow-lg shadow-red-900/40"
						onClick={fetchData}
					>
						Search
					</button>
				</div>

				{error && <div className="text-red-400 text-sm">{error}</div>}
				{loading && <div className="text-gray-300">Loading...</div>}

				{!loading && !error && data.length > 0 && (
					<div className="bg-[#111] border border-red-700/40 rounded-lg p-4 shadow-[0_12px_28px_rgba(0,0,0,0.55)]">
						<div className="flex gap-4 items-start">
							<div className="flex-1 min-w-0 space-y-3">
								{/* Legend */}
								<div className="flex items-center gap-3 text-xs text-gray-300">
									<span className="font-semibold">Legend:</span>
									{[
										{ c: "#ef4444", label: "< 6.0" },
										{ c: "#f97316", label: "6.0 - 7.5" },
										{ c: "#84cc16", label: "7.5 - 8.9" },
										{ c: "#22c55e", label: "9.0+" },
										{ c: "#374151", label: "N/A" },
									].map((item) => (
										<div key={item.label} className="flex items-center gap-1">
											<span className="w-4 h-4 rounded-sm" style={{ backgroundColor: item.c }} />
											<span>{item.label}</span>
										</div>
									))}
								</div>

								{/* Heatmap */}
								{(() => {
									const maxEpisodes = Math.max(
										...data.map((s) => Math.max(...(s.episodes || []).map((e) => e.episode || 0), 0))
									);
									return (
										<div
											className="overflow-x-auto bg-neutral-800 border border-neutral-700 rounded-lg p-4"
											style={{ scrollbarWidth: "thin" }}
										>
											<div
												className="grid gap-1"
												style={{
													gridTemplateColumns: `auto repeat(${maxEpisodes}, minmax(44px,1fr))`,
													minWidth: `${maxEpisodes * 48 + 80}px`,
												}}
											>
												{/* episode headers */}
												<div className="text-xs text-gray-400" />
												{Array.from({ length: maxEpisodes }, (_, i) => (
													<div key={`h-${i + 1}`} className="text-xs text-center text-gray-300 font-semibold">
														E{i + 1}
													</div>
												))}

												{/* seasons + cells */}
												{data.map((season) => (
													<React.Fragment key={season.season}>
														<div className="text-sm text-gray-200 font-semibold pr-2">Season {season.season}</div>
														{Array.from({ length: maxEpisodes }, (_, i) => {
															const ep = (season.episodes || []).find((e) => e.episode === i + 1);
															const ratingText = ep?.rating != null ? ep.rating : "N/A";
															const bg = colorForRating(ep?.rating);
															return (
																<div
																	key={`${season.season}-${i + 1}`}
																	title={
																		ep
																			? `S${season.season} E${ep.episode}: ${ep.title ?? "Untitled"} (${ratingText})`
																			: `S${season.season} E${i + 1}: N/A`
																	}
																	className="w-full h-11 rounded-sm flex items-center justify-center text-xs font-semibold text-gray-900"
																	style={{ background: bg }}
																>
																	{ratingText}
																</div>
															);
														})}
													</React.Fragment>
												))}
											</div>
										</div>
									);
								})()}
							</div>
							{(showMeta.poster || showMeta.title) && (
								<div className="w-48 flex-shrink-0 self-start bg-[#141414] border border-red-700/40 rounded-md p-3 space-y-2 shadow-lg shadow-black/50">
									{showMeta.poster && (
										<img
											src={showMeta.poster}
											alt={showMeta.title}
											className="w-full h-32 object-cover rounded border border-red-900/50"
										/>
									)}
									<div className="text-sm font-semibold text-center text-white">{showMeta.title}</div>
									{showMeta.plot && (
										<p className="text-xs text-gray-300 leading-relaxed text-center line-clamp-5">
											{showMeta.plot}
										</p>
									)}
								</div>
							)}
						</div>
					</div>
				)}
			</div>
		</div>
	);
}
