export const Reaction: React.FC<{}> = (props) => {
    return (
        <div className="bg-success" style={{ height: "40px" }}>
            <div className="d-flex justify-content-center w-100 h-100">
                <div className="d-flex align-items-center justify-content-center border border-black h-100" style={{ width: "calc(100% / 7)" }}>Like</div>
                <div className="d-flex align-items-center justify-content-center border border-black h-100" style={{ width: "calc(100% / 7)" }}>Comment</div>
            </div>
        </div>
    )
}
export default Reaction;