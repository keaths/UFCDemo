export const Header: React.FC<{}> = (props) => {
    return (
        <div className="d-flex h-50 w-100 bg-danger">
            <div className="w-50 border border-black h-100">
                <div className="d-flex justify-content-start align-items-center h-100 w-50 border border-black">
                    <div className="bg-success ms-2 me-1" style={{ borderRadius: "50%", height: "80px", width: "80px" }}></div>
                    <div>@usernmame</div>
                </div>
            </div>
            <div className="w-50 border border-black h-100">
                <div className="d-flex justify-content-end me-1 ms-auto w-25 border border-black">01/01/1999 23:00:00</div>
                <div className="d-flex justify-content-end me-1 ms-auto w-50 border border-black">Some Location, Some Country</div>
                <div className="d-flex justify-content-end me-1 ms-auto w-50 border border-black">Group Schedule</div>
            </div>
        </div>
    )
}
export default Header;